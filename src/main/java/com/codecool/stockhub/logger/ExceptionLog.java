package com.codecool.stockhub.logger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
@NoArgsConstructor
public class ExceptionLog {

    public void log(Exception e) {
        System.out.println(e);
    }
}
