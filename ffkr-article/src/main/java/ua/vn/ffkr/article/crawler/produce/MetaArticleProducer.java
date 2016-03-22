package ua.vn.ffkr.article.crawler.produce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;
import ua.vn.ffkr.article.crawler.fetch.Fetcher;
import ua.vn.ffkr.article.crawler.model.MetaArticle;
import ua.vn.ffkr.article.crawler.parser.ArticleListPageParser;
import ua.vn.ffkr.article.persistence.model.Article;
import ua.vn.ffkr.article.persistence.model.ProcessedMetaArticlePageUrl;
import ua.vn.ffkr.article.persistence.repository.ArticleRepository;
import ua.vn.ffkr.article.persistence.repository.ProcessedMetaArticlePageUrlRepository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static ua.vn.ffkr.article.config.CrawlerConfig.CRON_EVERY_15_SECONDS;

public class MetaArticleProducer implements Producer<Void, String> {

    private Logger logger = LoggerFactory.getLogger(MetaArticleProducer.class);

    @Value("${ffkr.article.crawler.start.page}")
    private String startPage;

    @Resource(name = "metaArticleQueue")
    private BlockingQueue<MetaArticle> queue;

    @Resource private Fetcher fetcher;
    @Resource private ArticleListPageParser articleListPageParser;
    @Resource private ArticleRepository articleRepository;
    @Resource private ProcessedMetaArticlePageUrlRepository processedMetaArticlePageUrlRepository;

    private final Lock lock = new ReentrantLock();

    @Override
    public Void produce(String pageUrl) {
        String articleListPage;
        SortedSet<MetaArticle> metaArticles;

        //TODO: Think about more efficient way to release current thread
        if(!queue.isEmpty()) {
            return null;
        }

        LocalDateTime lastSavedArticleDateTime =
                ofNullable(articleRepository.findFirstByOrderByDateAsc()).map(Article::getDate).orElse(now());

        LocalDateTime firstArticleDateTime =
                ofNullable(articleRepository.findFirstByOrderByDateDesc()).map(Article::getDate).orElse(now());

        do {
            articleListPage = fetcher.fetchPage(pageUrl).orElse(EMPTY);
            metaArticles = articleListPageParser.retrieveListOfMetaArticles(articleListPage);
            List<MetaArticle> collect = metaArticles.stream()
                    .filter(metaArticle -> metaArticle.getDate().isAfter(lastSavedArticleDateTime) ||
                            metaArticle.getDate().isBefore(firstArticleDateTime)).collect(toList());
            collect.forEach(queue::add);
            processedMetaArticlePageUrlRepository.save(ProcessedMetaArticlePageUrl.builder().url(pageUrl).build());
            pageUrl = articleListPageParser.retrieveNextPageUrl(articleListPage);
        } while (!isNull(articleListPage));

        return null;
    }

    @Scheduled(cron = CRON_EVERY_15_SECONDS)
    @Override
    public void execute() {
        lock.lock();
        try {
            // move to interceptor
            StopWatch timer = new StopWatch();
            timer.start();
            logger.info("Start producing {} data", MetaArticle.class.getSimpleName());
            String lastProcessedPage = ofNullable(processedMetaArticlePageUrlRepository.findFirstByOrderByUrlDesc())
                            .map(ProcessedMetaArticlePageUrl::getUrl).orElse(startPage);
            produce(lastProcessedPage);
            // move to interceptor
            timer.stop();
            logger.info("Finishing producing {} data. Total time - {} ms",
                    MetaArticle.class.getSimpleName(), timer.getTotalTimeMillis());
        } finally {
            lock.unlock();
        }
    }
}
