package com.codecool.stockhub.controller;


import com.codecool.stockhub.model.User;
import com.codecool.stockhub.service.HTTPConnection;
import com.codecool.stockhub.service.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CompanyController {

    @Autowired
    private HTTPConnection httpConnection;

    @Autowired
    private UserList userList;

    @GetMapping("/companies")
    public StringBuilder companyList(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(200);
        return httpConnection.getContent();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(User user){
        userList.registerUser(user);
        return user;
    }



}
