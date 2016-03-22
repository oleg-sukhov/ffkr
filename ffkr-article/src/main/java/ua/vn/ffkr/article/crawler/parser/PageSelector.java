package ua.vn.ffkr.article.crawler.parser;

public enum PageSelector {
    FIND_ALL_META_ARTICLES_ON_PAGE("ul.archive-list"),
    FIND_META_ARTICLES_NEXT_PAGE("div.paginator-holder > .page-next");

    PageSelector(String selector) {
        this.selector = selector;
    }

    private String selector;

    public String getSelector() {
        return selector;
    }
}
