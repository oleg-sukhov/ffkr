package ua.vn.ffkr.article.crawler.parser;

import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ua.vn.ffkr.article.crawler.model.MetaArticle;
import ua.vn.ffkr.article.persistence.model.Article;

import java.util.List;
import java.util.stream.Collectors;

public class ArticlePageParserImpl implements ArticlePageParser {

    @Override
    public Article retrieveArticle(String articlePage, MetaArticle metaArticle) {
        Document articleDocument = Jsoup.parse(articlePage);
        return Article.builder()
                .id(ObjectId.get())
                .url(metaArticle.getUrl())
                .date(metaArticle.getDate())
                .title(retrieveTitle(articleDocument))
                .description(retrieveDescription(articleDocument))
                .content(retrieveContent(articleDocument))
                .tags(retrieveTags(articleDocument))
                .images(retrieveImagesUrls(articleDocument))
                .build();
    }

    private String retrieveTitle(Document articleDocument) {
        return articleDocument.select("article.author-article > h1").first().text();
    }

    private String retrieveDescription(Document articleDocument) {
        return articleDocument.select("article.author-article > p.intro").first().text();
    }

    private String retrieveContent(Document articleDocument) {
        return articleDocument.select("div.article-text").first().text();
    }

    private List<String> retrieveTags(Document articleDocument) {
        return articleDocument.select("article.author-article > div.bottom-info > div.col > p > a")
                .stream()
                .map(Element::text)
                .collect(Collectors.toList());
    }

    private List<String> retrieveImagesUrls(Document articleDocument) {
        return articleDocument.select("article.author-article > div > img")
                .stream()
                .map(imageElement -> imageElement.attr("src"))
                .collect(Collectors.toList());
    }

}
