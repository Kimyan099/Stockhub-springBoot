package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.service.CompanyList;
import com.codecool.stockhub.service.HTTPConnection;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;


@RestController
public class CompanyController {

    private final ExceptionLog exceptionLog = new ExceptionLog();
    private static final String ORIGIN = "http://localhost:3000";
    private static final String COMPANIES_URL = "https://finnhub.io/api/v1/stock/symbol?exchange=US&token=bu21mlf48v6u9tetnbt0";

    @Autowired
    private HTTPConnection httpConnection;

    @Autowired
    private CompanyList companyList;


    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/companies")
    public List<Company> companyList(HttpServletResponse response) {
        String jsonResponse = httpConnection.getContent(COMPANIES_URL);
        try {
            companyList.filterData(jsonResponse);
            response.setStatus(200);
            return companyList.getCompanies();

        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
        }
            return Collections.emptyList();
    }
}
