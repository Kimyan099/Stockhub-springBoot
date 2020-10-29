package com.codecool.stockhub.service;

import com.codecool.stockhub.model.Company;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CompanyList {

    private List<Company> companies = new LinkedList<>();

    public List<Company> getCompanies() {
        return companies;
    }


    public void filterData(String content) throws JSONException {

        JSONArray contentJsonArray = new JSONArray(content);

        for (int i = 0; i < contentJsonArray.length(); i++) {
            Company company = new Company();
            JSONObject currentJsonObject = contentJsonArray.getJSONObject(i);

            company.setSymbol(currentJsonObject.getString("symbol"));
            company.setDescription(currentJsonObject.getString("description"));
            companies.add(company);


        }
    }

}
