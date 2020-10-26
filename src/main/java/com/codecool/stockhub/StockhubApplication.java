package com.codecool.stockhub;

import com.codecool.stockhub.service.HTTPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StockhubApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockhubApplication.class);

    @Autowired
    private HTTPConnection httpConnection;

    public static void main(String[] args) {
        SpringApplication.run(StockhubApplication.class, args);
    }

    @PostConstruct
    public void afterInit() {
        LOGGER.info(httpConnection.toString());
    }


}
