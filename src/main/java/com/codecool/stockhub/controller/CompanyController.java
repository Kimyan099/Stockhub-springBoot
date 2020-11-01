package com.codecool.stockhub.controller;


import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.service.CompanyList;
import com.codecool.stockhub.service.HTTPConnection;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private HTTPConnection httpConnection;

    @Autowired
    private CompanyList companyList;

    private static final String COMPANIES_URL = "https://finnhub.io/api/v1/stock/symbol?exchange=US&token=bu21mlf48v6u9tetnbt0";

    @ExceptionHandler({ JSONException.class, IllegalArgumentException.class, FileNotFoundException.class })
    @CrossOrigin("*")
    @GetMapping("/companies")
    public List<Company> companyList(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String jsonResponse = httpConnection.getContent(COMPANIES_URL);
        try {
            companyList.filterData(jsonResponse);
            response.setStatus(200);
            return companyList.getCompanies();
        } catch (Exception e) {
            new ExceptionLog(e);
            response.setStatus(400);
            response.getWriter().println("Companies not found");
            throw new FileNotFoundException("File not Found");
        }
    }
}
