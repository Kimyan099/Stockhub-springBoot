package com.codecool.companyservice;

import com.codecool.companyservice.logger.ExceptionLog;
import com.codecool.companyservice.service.CompanyList;
import com.codecool.companyservice.service.HTTPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class CompanyServiceApplication {

	private static final String COMPANIES_URL = "https://finnhub.io/api/v1/stock/symbol?exchange=US&token=bu21mlf48v6u9tetnbt0";

	@Autowired
	private ExceptionLog exceptionLog;

	@Autowired
	private HTTPConnection httpConnection;

	@Autowired
	private CompanyList companyList;

	String jsonResponse = httpConnection.getContent(COMPANIES_URL);


	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);

	}

	@Bean
	@Profile("production")
	public CommandLineRunner init() {

		return args -> {
			try {
				companyList.filterData(jsonResponse);
			} catch (IllegalArgumentException e) {
				exceptionLog.log(e);
			}

		};
	}
}
