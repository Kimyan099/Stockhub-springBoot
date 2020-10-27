package com.codecool.stockhub.controller;


import com.codecool.stockhub.model.User;
import com.codecool.stockhub.service.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserList userList;

    @PostMapping(value = "/add")
    public User addUser(@RequestBody User user){
        userList.registerUser(user);
        return user;
    }

}
