package com.codecool.stockhub.service;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Company;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedList;
import java.util.List;


@Service
public class CompanyList {

    private final List<Company> companies = new LinkedList<>();

    public List<Company> getCompanies() {
        return companies;
    }

    private final ExceptionLog exceptionLog = new ExceptionLog();


    public void filterData(String content) {
        try {
            JSONArray contentJsonArray = new JSONArray(content);

            for (int i = 0; i < contentJsonArray.length(); i++) {
                Company company = new Company();
                JSONObject currentJsonObject = contentJsonArray.getJSONObject(i);

                company.setSymbol(currentJsonObject.getString("symbol"));
                company.setDescription(currentJsonObject.getString("description"));
                companies.add(company);
            }
        } catch (JSONException e) {
            exceptionLog.log(e);
            throw new IllegalArgumentException("Content format is not valid");
        }
    }
}
