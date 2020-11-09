package com.codecool.stockhub.service;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.UserObject;
import com.codecool.stockhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserList {

    //private final ExceptionLog exceptionLog = new ExceptionLog();

    @Autowired
    private ExceptionLog exceptionLog;

    @Autowired
    private UserRepository userRepository;

    //private final List<User> users = new LinkedList<>();
    private UserObject loggedInUserObject;

    public void registerUser(UserObject userObject){
        userRepository.save(userObject);
    }

    public List<UserObject> getUsers() {
        return userRepository.findAll();
    }

    public UserObject getLoggedInUser() {
        return loggedInUserObject;
    }

    public void setLoggedInUser(UserObject loggedInUserObject) {
        this.loggedInUserObject = loggedInUserObject;
    }

    public UserObject getUserByEmail(String email) {
        for (UserObject userObject : getUsers()) {
            if(userObject.getEmail().equals(email)) {
                return userObject;
            }
        }
        return new UserObject();
    }

    public boolean isUserExist(String email) {
        for (UserObject userObject : getUsers()) {
            if (userObject.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public String checkIfCanLogIn(String email, String password) {
        try {
            for(UserObject userObject : getUsers()) {
                if (userObject.getEmail().equals(email)) {
                    if (userObject.getPassword().equals(password)){
                        setLoggedInUser(userObject);
                        return userObject.getName();
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            exceptionLog.log(e);
            throw new IllegalArgumentException("Email or password in wrong format");
        }
            return "";
    }
}
