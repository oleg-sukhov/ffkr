package ua.vn.ffkr.article.persistence.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "processedmetaarticlepages")
@Data
@Builder
public class ProcessedMetaArticlePageUrl {
    private ObjectId id;
    private String url;
}
