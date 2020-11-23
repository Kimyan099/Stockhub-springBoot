package com.codecool.stockhub;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Client;
import com.codecool.stockhub.model.Stock;
import com.codecool.stockhub.repository.ClientRepository;
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

import static com.codecool.stockhub.model.ApplicationUserRole.ADMIN;

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

    @Autowired
    private ClientRepository clientRepository;


    public static void main(String[] args) {
        SpringApplication.run(StockhubApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {

        return args -> {
            Stock apple = Stock.builder()
                    .price(100)
                    .symbol("AAPL")
                    .name("Apple")
                    .amount(200)
                    .imageLink("https://static.finnhub.io/logo/87cb30d8-80df-11ea-8951-00000000092a.png")
                    .build();

            Client admin = Client.builder()
                    .name("admin")
                    .email("admin@gmail.com")
                    .password("123")
                    .authorities(ADMIN.getGrantedAuthorities())
                    .stock(apple)
                    .build();

            apple.setClient(admin);
            clientRepository.saveAndFlush(admin);

            String jsonResponse = httpConnection.getContent(COMPANIES_URL);

            String newsJsonResponse = httpConnection.getContent(NEWS_URL); // for market-news

            try {
                companyList.filterData(jsonResponse);

                newsList.getData(newsJsonResponse); // for market-news
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
