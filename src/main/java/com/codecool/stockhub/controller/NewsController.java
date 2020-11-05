package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.service.HTTPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletResponse;


@RestController
public class NewsController {

    private static final String ORIGIN = "http://localhost:3000";

    ExceptionLog exceptionLog = new ExceptionLog();

    @Autowired
    private HTTPConnection httpConnection;

    private static final String NEWS_URL = "https://finnhub.io/api/v1/news?category=general&token=bu2rf9f48v6pqlhnnvtg";

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/news")
    public String newsList(HttpServletResponse response) {
        try {
            response.setStatus(200);
            return httpConnection.getContent(NEWS_URL);

        }catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid URL");

        }catch (RestClientException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new RestClientException("Request fails because of server response error");
        }
    }
}
