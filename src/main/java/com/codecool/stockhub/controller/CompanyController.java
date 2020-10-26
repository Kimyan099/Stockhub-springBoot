package com.codecool.stockhub.controller;


import com.codecool.stockhub.service.HTTPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private HTTPConnection httpConnection;

    @GetMapping("/companies")
    public StringBuilder companyList(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(200);
        return httpConnection.getContent();
    }



}
