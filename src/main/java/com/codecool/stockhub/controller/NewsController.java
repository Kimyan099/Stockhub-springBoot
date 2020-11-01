package com.codecool.stockhub.controller;


import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.service.HTTPConnection;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class NewsController {

    @Autowired
    private HTTPConnection httpConnection;

    private static final String NEWS_URL = "https://finnhub.io/api/v1/news?category=general&token=bu2rf9f48v6pqlhnnvtg";

    @ExceptionHandler({ IllegalArgumentException.class, FileNotFoundException.class })
    @CrossOrigin("*")
    @GetMapping("/news")
    public String newsList(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.setStatus(200);
            return httpConnection.getContent(NEWS_URL);

        }catch (Exception e) {
            response.setStatus(400);
            response.getWriter().println("News not found");
            new ExceptionLog(e);
            throw new FileNotFoundException("File not found");
        }

    }
}
