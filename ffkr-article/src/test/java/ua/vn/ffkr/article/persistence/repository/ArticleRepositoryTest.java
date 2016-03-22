package ua.vn.ffkr.article.persistence.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.vn.ffkr.article.config.ArticleConfig;
import ua.vn.ffkr.article.persistence.model.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ArticleConfig.class)
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository newsRepository;

    @Test
    public void shouldOkSaveAndFindNews() {
        Article article = Article.builder()
                .title("Article Title")
                .content("")
                .build();
        newsRepository.save(article);
    }
}
