package ua.vn.ffkr.article.crawler.consume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;
import ua.vn.ffkr.article.crawler.fetch.Fetcher;
import ua.vn.ffkr.article.crawler.handle.Handler;
import ua.vn.ffkr.article.crawler.model.MetaArticle;
import ua.vn.ffkr.article.crawler.parser.ArticlePageParser;
import ua.vn.ffkr.article.persistence.model.Article;
import ua.vn.ffkr.article.persistence.repository.ArticleRepository;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static ua.vn.ffkr.article.config.CrawlerConfig.CRON_EVERY_5_SECONDS;

public class MetaArticleConsumer implements Consumer<MetaArticle, Void> {
    private Logger logger = LoggerFactory.getLogger(MetaArticleConsumer.class);

    @Resource(name = "metaArticleQueue")
    private BlockingQueue<MetaArticle> queue;

    @Resource private Fetcher fetcher;
    @Resource private ArticlePageParser newsPageParser;
    @Resource private ArticleRepository newsRepository;
    @Resource private Handler<Article, Article> articleImageHandler;

    @Override
    public Void consume(MetaArticle metaArticle) {
        ofNullable(metaArticle).ifPresent(value -> {
            StopWatch timer = new StopWatch();
            timer.start();
            logger.info("Start consuming : " + metaArticle.toString());
            String newsPage = fetcher.fetchPage(metaArticle.getUrl()).orElse(EMPTY);
            Article article = newsPageParser.retrieveArticle(newsPage, metaArticle);
            newsRepository.save(articleImageHandler.handle(article));
            timer.stop();
            logger.info("Finish consuming : {}. Spent time - {} ", metaArticle.toString(), timer.getTotalTimeMillis());
        });
        return null;
    }

    @Scheduled(cron = CRON_EVERY_5_SECONDS)
    @Override
    public void execute() {

        StopWatch timer = new StopWatch();
        timer.start();
        MetaArticle metaArticle = null;

        do {
            try {
                metaArticle = queue.poll(2000, TimeUnit.MILLISECONDS);
                consume(metaArticle);
            } catch (InterruptedException e) {
                logger.warn("Interrupted exception was occurred " + e);
            }
        } while (metaArticle != null);

        timer.stop();
        logger.info("Finishing consuming {} data. Total time - {} ms",
                MetaArticle.class.getSimpleName(), timer.getTotalTimeMillis());

    }
}
