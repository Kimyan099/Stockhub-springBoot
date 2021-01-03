package com.codecool.newsregistry;

import com.codecool.newsregistry.logger.ExceptionLog;
import com.codecool.newsregistry.service.HTTPConnection;
import com.codecool.newsregistry.service.NewsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class NewsRegistryApplication {

	private static final String NEWS_URL = "https://finnhub.io/api/v1/news?category=general&token=bu2rf9f48v6pqlhnnvtg";


	@Autowired
	private ExceptionLog exceptionLog;

	@Autowired
	private HTTPConnection httpConnection;

	@Autowired
	private NewsList newsList;

	public static void main(String[] args) {
		SpringApplication.run(NewsRegistryApplication.class, args);
	}


	@Bean
	@Profile("production")
	public CommandLineRunner init() {

		return args -> {
			String newsJsonResponse = httpConnection.getContent(NEWS_URL); // for market-news
			try {
				newsList.getData(newsJsonResponse); // for market-news
			} catch (IllegalArgumentException e) {
				exceptionLog.log(e);
			}
		};
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
}
