package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.repository.CompanyRepository;
import com.codecool.stockhub.service.CompanyList;
import com.codecool.stockhub.service.HTTPConnection;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;


@RestController
public class CompanyController {

    private final ExceptionLog exceptionLog = new ExceptionLog();
    private static final String ORIGIN = "http://localhost:3000";

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyList companyList;

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/companies")
    public List<Company> companyList(HttpServletResponse response) {
        return companyRepository.findAll();

    }
}
