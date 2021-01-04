package com.codecool.newsregistry.controller;

import com.codecool.newsregistry.model.News;
import com.codecool.newsregistry.repository.NewsRepository;
import com.codecool.newsregistry.service.NewsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class NewsController {

//    private static final String ORIGIN = "http://localhost:3000";
    private static final String ORIGIN = "*";

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsList newsList;

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/category/allnews/orderby/desc")
    public List<News> newsListDESC() {
        return newsRepository.getAllNewsDESC();
    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/category/allnews/orderby/asc")
    public List<News> newsListASC() {
        return newsRepository.getAllNewsASC();
    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/category/{categoryName}/orderby/asc")
    public List<News> newsListByCategoryASC(@PathVariable("categoryName") String categoryName) {
        return newsRepository.getNewsByCategoryASC(categoryName);
    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/category/{categoryName}/orderby/desc")
    public List<News> newsListByCategoryDESC(@PathVariable("categoryName") String categoryName) {
        return newsRepository.getNewsByCategoryDESC(categoryName);
    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/allcategories")
    public List<String> allNewsCategories() {
        return newsRepository.getAllNewsCategories();
    }
}
