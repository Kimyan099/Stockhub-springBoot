package com.codecool.stockhub;

import com.codecool.stockhub.service.CompanyList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CompanyListConfiguration {
    @Bean
    public CompanyList companyListGetter() {
        return new CompanyList();
    }
}
