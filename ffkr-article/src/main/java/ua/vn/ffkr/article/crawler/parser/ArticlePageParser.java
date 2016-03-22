package ua.vn.ffkr.article.crawler.parser;

import ua.vn.ffkr.article.crawler.model.MetaArticle;
import ua.vn.ffkr.article.persistence.model.Article;

public interface ArticlePageParser {
    Article retrieveArticle(String articlePage, MetaArticle metaArticle);
}
