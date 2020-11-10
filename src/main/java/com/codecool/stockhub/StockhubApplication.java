package com.codecool.stockhub;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.repository.CompanyRepository;
import com.codecool.stockhub.service.CompanyList;
import com.codecool.stockhub.service.HTTPConnection;
import com.codecool.stockhub.service.NewsList;
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

    private static final String NEWS_URL = "https://finnhub.io/api/v1/news?category=general&token=bu2rf9f48v6pqlhnnvtg";

    private static final Logger LOGGER = LoggerFactory.getLogger(StockhubApplication.class);

    @Autowired
    private CompanyList companyList;

    @Autowired
    private NewsList newsList;

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

        String companiesJsonResponse = httpConnection.getContent(COMPANIES_URL);
        String newsJsonResponse = httpConnection.getContent(NEWS_URL);

        return args -> {
            try {
                companyList.filterData(companiesJsonResponse);
                newsList.getData(newsJsonResponse);

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
