package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.User;
import com.codecool.stockhub.service.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;


@RestController
public class UserController {


    @Autowired
    private UserList userList;

    @ExceptionHandler({ IllegalArgumentException.class, NullPointerException.class, IndexOutOfBoundsException.class})
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/add")
    public void addUser(@RequestBody User user, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        try {
            userList.registerUser(user);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(400);
            new ExceptionLog(e.getMessage(), e);
        }
    }

    @ExceptionHandler({ NullPointerException.class, IndexOutOfBoundsException.class, IllegalArgumentException.class })
    @GetMapping("/users")
    public List<User> getUsers(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.setStatus(200);
            return userList.getUsers();
        } catch (Exception e) {
            response.setStatus(400);
            new ExceptionLog(e.getMessage(), e);
        }
            return Collections.emptyList();
    }

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IndexOutOfBoundsException.class })
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/user")
    public boolean update(String email, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.setStatus(200);
            return userList.isUserExist(email);
        } catch (Exception e) {
            response.setStatus(400);
            new ExceptionLog(e.getMessage(), e);
        }
            return false;
    }

    @ExceptionHandler({ NullPointerException.class, IndexOutOfBoundsException.class, IllegalArgumentException.class})
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login")
    public String update(String email, String password, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.setStatus(200);
            return userList.checkIfCanLogIn(email, password);
        } catch (Exception e) {
            response.setStatus(400);
            new ExceptionLog(e.getMessage(), e);
        }
            return "";
    }
}
