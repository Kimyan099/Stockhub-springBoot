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
        int counter = 0;
        int length = 200;
        int index = 0;
        try {
            JSONArray contentJsonArray = new JSONArray(content);

            while (counter != length) {

                JSONObject currentJsonObject = contentJsonArray.getJSONObject(index);

                if (currentJsonObject.getString("symbol").charAt(0) == 'A' && currentJsonObject.getString("description").length() < 30) {
                    Company company = Company.builder()
                            .description(currentJsonObject.getString("description"))
                            .symbol(currentJsonObject.getString("symbol"))
                            .build();
                    companyRepository.save(company);
                    counter++;
                }
                index++;
            }
        } catch (JSONException e) {
            exceptionLog.log(e);
            throw new IllegalArgumentException("Content format is not valid");
        }
    }
}
