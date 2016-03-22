package ua.vn.ffkr.ui.starter;

import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.io.ClassPathResource;
import ua.vn.ffkr.ui.config.UIConfig;

import java.util.Collections;

import static java.util.Collections.singleton;

@SpringBootApplication
@EnableEurekaClient
public class UIApplication {
    private static final String BANNER_PATH = "ua/vn/ffkr/ui/config/ffkr_banner.txt";

    public static void main(String[] args) {
        SpringApplication springApp = new SpringApplication();
        springApp.setBanner(loadBanner());
        springApp.setSources(singleton(UIConfig.class));
        springApp.run(args);
    }

    private static Banner loadBanner() {
        return new ResourceBanner(new ClassPathResource(BANNER_PATH));
    }
}
