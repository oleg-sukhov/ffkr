package ua.vn.ffkr.article.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleDto {
    private String id;
    private String date;
    private String title;
    private String description;
    private List<String> tags;
}
