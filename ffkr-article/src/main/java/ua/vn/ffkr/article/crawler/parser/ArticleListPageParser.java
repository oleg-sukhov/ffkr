package ua.vn.ffkr.article.crawler.parser;

import ua.vn.ffkr.article.crawler.model.MetaArticle;

import java.util.List;
import java.util.SortedSet;

public interface ArticleListPageParser {
    SortedSet<MetaArticle> retrieveListOfMetaArticles(String articleListPage);
    String retrieveNextPageUrl(String articleListPage);
}
