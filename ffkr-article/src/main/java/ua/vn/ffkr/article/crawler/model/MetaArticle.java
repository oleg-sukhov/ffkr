package ua.vn.ffkr.article.crawler.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

@Data
@ToString
@Builder
public class MetaArticle implements Comparable<MetaArticle> {

    private LocalDateTime date;
    private String url;

    @Override
    public int compareTo(MetaArticle that) {
        return date.compareTo(that.getDate());
    }
}
