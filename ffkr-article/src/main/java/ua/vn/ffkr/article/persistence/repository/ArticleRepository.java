package ua.vn.ffkr.article.persistence.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.vn.ffkr.article.persistence.model.Article;

public interface ArticleRepository extends MongoRepository<Article, ObjectId> {
    Article findFirstByOrderByDateAsc();
    Article findFirstByOrderByDateDesc();
}
