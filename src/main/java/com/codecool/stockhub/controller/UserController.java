package com.codecool.stockhub.controller;


import com.codecool.stockhub.model.User;
import com.codecool.stockhub.service.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserList userList;


    @CrossOrigin(origins = "*")
    @PostMapping(value = "/add")
    public User addUser(@RequestBody User user, HttpServletResponse response) {
        userList.registerUser(user);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setStatus(200);
        return user;
    }

    @GetMapping("/users")
    public List<User> getUsers(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(200);
        return userList.getUsers();
    }

}
