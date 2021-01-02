package com.codecool.companyservice.service;


import com.codecool.companyservice.logger.ExceptionLog;
import com.codecool.companyservice.model.Company;
import com.codecool.companyservice.repository.CompanyRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
