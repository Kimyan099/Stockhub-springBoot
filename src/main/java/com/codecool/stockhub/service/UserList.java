package com.codecool.stockhub.service;

import com.codecool.stockhub.model.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class UserList {

    private List<User> users = new LinkedList<>();

    public void registerUser(User user){
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
