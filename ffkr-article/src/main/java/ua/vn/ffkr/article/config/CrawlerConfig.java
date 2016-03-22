package ua.vn.ffkr.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import ua.vn.ffkr.article.crawler.consume.Consumer;
import ua.vn.ffkr.article.crawler.consume.MetaArticleConsumer;
import ua.vn.ffkr.article.crawler.fetch.FetcherImpl;
import ua.vn.ffkr.article.crawler.handle.ArticleImageHandler;
import ua.vn.ffkr.article.crawler.handle.Handler;
import ua.vn.ffkr.article.crawler.model.MetaArticle;
import ua.vn.ffkr.article.crawler.parser.ArticleListPageParser;
import ua.vn.ffkr.article.crawler.parser.ArticleListPageParserImpl;
import ua.vn.ffkr.article.crawler.parser.ArticlePageParser;
import ua.vn.ffkr.article.crawler.parser.ArticlePageParserImpl;
import ua.vn.ffkr.article.crawler.produce.MetaArticleProducer;
import ua.vn.ffkr.article.crawler.produce.Producer;
import ua.vn.ffkr.article.persistence.model.Article;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
@ComponentScan("ua.vn.ffkr.article.crawler")
@EnableScheduling
public class CrawlerConfig implements SchedulingConfigurer {
    public static final String CRON_EVERY_5_SECONDS = "*/5 * * * * *";
    public static final String CRON_EVERY_15_SECONDS = "*/15 * * * * *";

    @Bean
    public FetcherImpl htmlContentFetcher() {
        return new FetcherImpl();
    }

    @Bean
    public Producer metaArticleProducer() {
        return new MetaArticleProducer();
    }

    @Bean
    public Consumer metaArticleConsumer() {
        return new MetaArticleConsumer();
    }

    @Bean
    public Queue<MetaArticle> metaArticleQueue() {
        return new ArrayBlockingQueue<>(5000);
    }

    @Bean
    public ArticleListPageParser articleListPageParser() {
        return new ArticleListPageParserImpl();
    }

    @Bean
    public ArticlePageParser articlePageParser() {
        return new ArticlePageParserImpl();
    }

    @Bean
    public Handler<Article, Article> articleImageHandler() {
        return new ArticleImageHandler();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(5));
    }
}
