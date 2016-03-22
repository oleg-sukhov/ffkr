package ua.vn.ffkr.article.crawler.handle;

public interface Handler<I, O> {
    O handle(I data);
}
