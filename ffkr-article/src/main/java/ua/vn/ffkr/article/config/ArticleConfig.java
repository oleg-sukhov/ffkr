package ua.vn.ffkr.article.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ua.vn.ffkr.article")
@Import({CrawlerConfig.class, MongoConfig.class})
public class ArticleConfig {

}