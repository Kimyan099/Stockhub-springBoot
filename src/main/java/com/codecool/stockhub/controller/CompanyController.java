package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.model.Stock;
import com.codecool.stockhub.repository.CompanyRepository;
import com.codecool.stockhub.service.CompanyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class CompanyController {

    private static final String ORIGIN = "http://localhost:3000";

    @Autowired
    private ExceptionLog exceptionLog;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyList companyList;

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/companies")
    public List<Company> companyList(HttpServletResponse response) {
        return companyRepository.findAll();

    }

    @CrossOrigin(origins = ORIGIN)
    @GetMapping("/companies/{symbol}")
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

