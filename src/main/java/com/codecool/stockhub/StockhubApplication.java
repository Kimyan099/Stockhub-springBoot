package com.codecool.stockhub;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Client;
import com.codecool.stockhub.model.Stock;
import com.codecool.stockhub.repository.ClientRepository;
import com.codecool.stockhub.service.HTTPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

import static com.codecool.stockhub.model.ApplicationUserRole.ADMIN;

@SpringBootApplication
public class StockhubApplication {



    private static final Logger LOGGER = LoggerFactory.getLogger(StockhubApplication.class);


    @Autowired
    private ExceptionLog exceptionLog;

    @Autowired
    private HTTPConnection httpConnection;

    @Autowired
    private ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    public StockhubApplication() {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


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
                    .password(passwordEncoder.encode("admin"))
                    .authorities(ADMIN.getGrantedAuthorities())
                    .stock(apple)
                    .build();

            apple.setClient(admin);
            clientRepository.saveAndFlush(admin);

        };
    }

    @PostConstruct
    public void afterInit() {
        LOGGER.info(httpConnection.toString());
    }
}
