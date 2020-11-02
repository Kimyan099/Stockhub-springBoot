package com.codecool.stockhub.service;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.User;
import com.sun.el.stream.Optional;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    public User getUserByEmail(String email) {
        for (User user : users) {
            if(user.getEmail().equals(email)) {
                return user;
            }
        }
        new ExceptionLog("User not found");
        return new User();
    }

    public boolean isUserExist(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @ExceptionHandler({ IllegalArgumentException.class, ArrayIndexOutOfBoundsException.class, NullPointerException.class})
    public String checkIfCanLogIn(String email, String password) {
        try {
            for(User user : users) {
                if (user.getEmail().equals(email)) {
                    if (user.getPassword().equals(password)){
                        setLoggedInUser(user);
                        return user.getName();
                    }
                }
            }
        } catch (Exception e) {
            new ExceptionLog(e.getMessage(), e);
            throw new IllegalArgumentException("Email or password in wrong format");
        }
            return "";
    }
}
