package ua.vn.ffkr.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private RestTemplate articleRestTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Object findArticles() {
        ResponseEntity<Object> forEntity = articleRestTemplate.getForEntity("http://ffkr-article/articles", Object.class);
        return forEntity;

    }




}
