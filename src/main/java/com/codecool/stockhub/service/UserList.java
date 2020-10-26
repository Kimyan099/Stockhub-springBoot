package com.codecool.stockhub.service;

import com.codecool.stockhub.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserList {

    private List<User> users = new ArrayList<>();

    public void registerUser(User user){
        users.add(user);
    }
}
