package com.codecool.stockhub;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.repository.CompanyRepository;
import com.codecool.stockhub.service.CompanyList;
import com.codecool.stockhub.service.HTTPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class StockhubApplication {

    private static final String COMPANIES_URL = "https://finnhub.io/api/v1/stock/symbol?exchange=US&token=bu21mlf48v6u9tetnbt0";

    private static final Logger LOGGER = LoggerFactory.getLogger(StockhubApplication.class);

    @Autowired
    private CompanyList companyList;

    @Autowired
    private ExceptionLog exceptionLog;

    @Autowired
    private HTTPConnection httpConnection;


    public static void main(String[] args) {
        SpringApplication.run(StockhubApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {

        return args -> {
            String jsonResponse = httpConnection.getContent(COMPANIES_URL);
            try {
                companyList.filterData(jsonResponse);
            } catch (IllegalArgumentException e) {
                exceptionLog.log(e);
            }
        };
    }

    @PostConstruct
    public void afterInit() {
        LOGGER.info(httpConnection.toString());
    }
}
