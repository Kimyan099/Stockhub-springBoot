package com.codecool.stockhub.controller;


import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.service.CompanyList;
import com.codecool.stockhub.service.HTTPConnection;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private HTTPConnection httpConnection;

    @Autowired
    private CompanyList companyList;

    private static final String COMPANIES_URL = "https://finnhub.io/api/v1/stock/symbol?exchange=US&token=bu21mlf48v6u9tetnbt0";

    @CrossOrigin("*")
    @GetMapping("/companies")
    public List<Company> companyList(HttpServletResponse response) throws IOException, JSONException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(200);
        String jsonResponse = httpConnection.getContent(COMPANIES_URL);
        companyList.filterData(jsonResponse);
        return companyList.getCompanies();
    }






}
