package com.codecool.companyservice.controller;


import com.codecool.companyservice.logger.ExceptionLog;
import com.codecool.companyservice.model.Company;
import com.codecool.companyservice.repository.CompanyRepository;
import com.codecool.companyservice.service.CompanyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class CompanyController {

//    private static final String ORIGIN = "http://localhost:3000";
    private static final String ORIGIN = "*";

    @Autowired
    private ExceptionLog exceptionLog;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyList companyList;

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("")
    public List<Company> companyList(HttpServletResponse response) {
        return companyRepository.findAll();

    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/{symbol}")
    public String companyBySymbol(@PathVariable String symbol, HttpServletResponse response) {
        try {
            response.setStatus(200);
            return companyRepository.getCompanyBySymbol(symbol).getSymbol();

        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid id!");
        }

    }
}

