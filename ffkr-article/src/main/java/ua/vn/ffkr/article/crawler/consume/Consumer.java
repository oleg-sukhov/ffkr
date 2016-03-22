package ua.vn.ffkr.article.crawler.consume;

public interface Consumer<I, O> {
    O consume(I input);
    void execute();
}
