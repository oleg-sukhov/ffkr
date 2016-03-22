package ua.vn.ffkr.article.crawler.fetch;

import java.io.IOException;

public class LoadDataException extends IOException {

    public LoadDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
