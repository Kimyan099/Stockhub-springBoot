package com.codecool.stockhub.service;

import com.codecool.stockhub.logger.ExceptionLog;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class HTTPConnection {

    private final ExceptionLog exceptionLog = new ExceptionLog();

    public String getContent(String api) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(api, String.class);
        } catch (IllegalArgumentException e) {
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid URL");
        } catch (RestClientException e) {
            exceptionLog.log(e);
            throw new RestClientException("Request fails because of server response error");
        }
    }
}
