package ua.vn.ffkr.article.persistence.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.vn.ffkr.article.dto.ArticleDto;
import ua.vn.ffkr.article.mapper.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "articles")
@Data
@Builder
public class Article implements Mapper<ArticleDto> {
    private ObjectId id;
    private LocalDateTime date;
    private String url;
    private String title;
    private String description;
    private String content;
    private List<String> tags;
    private List<String> images;

    @Override
    public ArticleDto map() {
        return ArticleDto.builder()
                .id(id.toString())
                .date(date.toString())
                .title(title)
                .description(description)
                .tags(tags)
                .build();
    }
}
