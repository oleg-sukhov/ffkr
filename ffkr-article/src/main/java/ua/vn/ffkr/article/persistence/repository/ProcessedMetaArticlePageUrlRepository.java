package ua.vn.ffkr.article.persistence.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.vn.ffkr.article.persistence.model.ProcessedMetaArticlePageUrl;

public interface ProcessedMetaArticlePageUrlRepository extends MongoRepository<ProcessedMetaArticlePageUrl, ObjectId> {
    ProcessedMetaArticlePageUrl findFirstByOrderByUrlDesc();
}
