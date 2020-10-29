package com.codecool.stockhub.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class HTTPConnection {

    public String getContent(String api) throws IllegalArgumentException {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(api, String.class);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid URL");
        }

    }
}
