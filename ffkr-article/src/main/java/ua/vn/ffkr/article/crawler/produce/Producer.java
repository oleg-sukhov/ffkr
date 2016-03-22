package ua.vn.ffkr.article.crawler.produce;

public interface Producer<T, S> {
    T produce(S source);
    void execute();
}
