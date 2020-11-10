package com.codecool.stockhub.service;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.repository.CompanyRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedList;
import java.util.List;


@Service

public class CompanyList {

    @Autowired
    private CompanyRepository companyRepository;


    private final ExceptionLog exceptionLog = new ExceptionLog();


    public void filterData(String content) {

        int length = 200;
        try {
            JSONArray contentJsonArray = new JSONArray(content);

            for (int i = 0; i < length; i++) {

                JSONObject currentJsonObject = contentJsonArray.getJSONObject(i);

                Company company = Company.builder()
                        .description(currentJsonObject.getString("description"))
                        .symbol(currentJsonObject.getString("symbol"))
                        .build();
                companyRepository.save(company);
            }
        } catch (JSONException e) {
            exceptionLog.log(e);
            throw new IllegalArgumentException("Content format is not valid");
        }
    }
}
