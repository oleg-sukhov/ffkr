package ua.vn.ffkr.article.service.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.vn.ffkr.article.dto.ArticleDto;
import ua.vn.ffkr.article.persistence.model.Article;
import ua.vn.ffkr.article.persistence.repository.ArticleRepository;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping("/articles")
@RestController
public class ArticleEndPoint {

    @Resource
    private ArticleRepository articleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<ArticleDto> findArticles() {
        return articleRepository.findAll().stream().map(Article::map).collect(toList());
    }


    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public List<ArticleDto> findArticleById(@PathVariable("id") String id) {
        return articleRepository.findAll().stream().map(Article::map).collect(toList());
    }
}
