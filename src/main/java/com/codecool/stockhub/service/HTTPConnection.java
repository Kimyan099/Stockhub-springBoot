package com.codecool.stockhub.service;

import com.codecool.stockhub.logger.ExceptionLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class HTTPConnection {

    @ExceptionHandler({ IllegalArgumentException.class })
    public String getContent(String api) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(api, String.class);
        } catch (Exception e) {
            new ExceptionLog(e);
            throw new IllegalArgumentException("Invalid URL");
        }
    }
}
