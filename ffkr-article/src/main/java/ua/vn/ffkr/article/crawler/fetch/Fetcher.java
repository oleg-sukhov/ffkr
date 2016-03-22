package ua.vn.ffkr.article.crawler.fetch;

import ua.vn.ffkr.article.crawler.model.BufferedImageWrapper;

import java.io.IOException;
import java.util.Optional;

public interface Fetcher {
    Optional<String> fetchPage(String url);
    Optional<BufferedImageWrapper> loadImage(String url);
}
