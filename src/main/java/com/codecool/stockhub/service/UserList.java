package com.codecool.stockhub.service;

import com.codecool.stockhub.model.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class UserList {

    private List<User> users = new LinkedList<>();
    private User loggedInUser;

    public void registerUser(User user){
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean isUserExist(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public String checkIfCanLogIn(String email, String password) {
        for(User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)){
                    setLoggedInUser(user);
                    return user.getName();
                }
            }
        }
        return "Guest";
    }
}
