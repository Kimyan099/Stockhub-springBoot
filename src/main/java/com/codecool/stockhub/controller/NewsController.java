package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.News;
import com.codecool.stockhub.repository.NewsRepository;
import com.codecool.stockhub.service.HTTPConnection;
import com.codecool.stockhub.service.NewsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class NewsController {

    private static final String ORIGIN = "http://localhost:3000";

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsList newsList;

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/news/category/allnews/orderby/desc")
    public List<News> newsListDESC() {
        return newsRepository.getAllNewsDESC();
    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/news/category/allnews/orderby/asc")
    public List<News> newsListASC() {
        return newsRepository.getAllNewsASC();
    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/news/category/{categoryName}/orderby/asc")
    public List<News> newsListByCategoryASC(@PathVariable("categoryName") String categoryName) {
        return newsRepository.getNewsByCategoryASC(categoryName);
    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/news/category/{categoryName}/orderby/desc")
    public List<News> newsListByCategoryDESC(@PathVariable("categoryName") String categoryName) {
        return newsRepository.getNewsByCategoryDESC(categoryName);
    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/news/allcategories")
    public List<String> allNewsCategories() {
        return newsRepository.getAllNewsCategories();
    }
}
