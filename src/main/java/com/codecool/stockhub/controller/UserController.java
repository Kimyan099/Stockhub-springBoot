package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.User;
import com.codecool.stockhub.service.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class UserController {

    private static final String ORIGIN = "http://localhost:3000";

    @Autowired
    private UserList userList;

    @Autowired
    private ExceptionLog exceptionLog;

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/add")
    public void addUser(@RequestBody User user, HttpServletResponse response) {
        try {
            userList.registerUser(user);
            response.setStatus(200);

        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid user");

        } catch (NullPointerException e) {
            response.setStatus(400);
            throw new NullPointerException("User not created");
        }
    }

    @GetMapping("/users")
    public List<User> getUsers(HttpServletResponse response){
        try {
            response.setStatus(200);
            return userList.getUsers();

        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Illegal arguments in user list");

        } catch (IndexOutOfBoundsException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/user")
    public boolean isUserExist(String email, HttpServletResponse response) {
        try {
            response.setStatus(200);
            return userList.isUserExist(email);

        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Email in invalid format");
        }
    }

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/login")
    public String update(String email, String password, HttpServletResponse response) {
        try {
            response.setStatus(200);
            return userList.checkIfCanLogIn(email, password);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid email or password");
        }
    }
}
