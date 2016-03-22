package ua.vn.ffkr.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import ua.vn.ffkr.article.config.ArticleConfig;

import static java.util.Collections.singleton;

@SpringBootApplication
@EnableEurekaClient
public class ArticleApplication {
    private static final Logger logger = LoggerFactory.getLogger(ArticleApplication.class);
    public static final String BANNER_PATH = "ua/vn/ffkr/article/config/ffkr_article_banner.txt";

    public static void main(String[] args) {
        SpringApplication springApp = new SpringApplication();
        springApp.setBanner(loadBanner());
        springApp.setSources(singleton(ArticleConfig.class));
        springApp.run(args);
        logger.debug("Application <{}> has been started", "ffkr");
    }

    public static Banner loadBanner() {
        return new ResourceBanner(new ClassPathResource(BANNER_PATH));
    }


}
