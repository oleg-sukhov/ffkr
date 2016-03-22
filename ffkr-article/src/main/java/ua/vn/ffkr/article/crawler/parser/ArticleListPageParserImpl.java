package ua.vn.ffkr.article.crawler.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.vn.ffkr.article.crawler.model.MetaArticle;

import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.stream.Collectors.toCollection;
import static ua.vn.ffkr.article.crawler.parser.PageSelector.FIND_ALL_META_ARTICLES_ON_PAGE;
import static ua.vn.ffkr.article.crawler.parser.PageSelector.FIND_META_ARTICLES_NEXT_PAGE;
import static ua.vn.ffkr.article.util.DateUtil.fromPattern;

public class ArticleListPageParserImpl implements ArticleListPageParser {
    private Logger logger = LoggerFactory.getLogger(ArticleListPageParserImpl.class);

    @Override
    public SortedSet<MetaArticle> retrieveListOfMetaArticles(String articleListPage) {
        Document articleListPageDocument = Jsoup.parse(articleListPage);
        return retrieveAllMetaArticles(articleListPageDocument);
    }

    @Override
    public String retrieveNextPageUrl(String newsListPage) {
        Document articleListPageDocument = Jsoup.parse(newsListPage);
        return retrieveNextMetaArticlesPage(articleListPageDocument);
    }

    private SortedSet<MetaArticle> retrieveAllMetaArticles(Document articlesListPageDocument) {
        Elements lightWeightNewsElements =
                articlesListPageDocument.select(FIND_ALL_META_ARTICLES_ON_PAGE.getSelector())
                .first()
                .children();

        return lightWeightNewsElements
                .stream()
                .map(this::transformToMetaArticle)
                .sorted()
                .collect(toCollection(TreeSet::new));
    }

    private MetaArticle transformToMetaArticle(Element metaArticleElement) {
        MetaArticle metaArticle = MetaArticle.builder()
                .url(retrieveArticleUrl(metaArticleElement))
                .date(fromPattern(retrieveArticleDateTime(metaArticleElement)))
                .build();

        logger.info("Found MetaArticle: {}", metaArticle);
        return metaArticle;
    }

    private String retrieveArticleUrl(Element metaArticleElement) {
        return metaArticleElement.select("a").first().attr("href");
    }

    private String retrieveArticleDateTime(Element metaArticleElement) {
        return metaArticleElement.select("p.date").first().text();
    }

    private String retrieveNextMetaArticlesPage(Document currentMetaArticleListPage) {
        return currentMetaArticleListPage.select(FIND_META_ARTICLES_NEXT_PAGE.getSelector()).first().attr("href");
    }

}
