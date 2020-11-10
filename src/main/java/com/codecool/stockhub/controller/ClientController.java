package com.codecool.stockhub.controller;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.Client;
import com.codecool.stockhub.model.Stock;
import com.codecool.stockhub.service.ClientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class ClientController {

    private static final String ORIGIN = "http://localhost:3000";

    @Autowired
    private ClientList clientList;


    @Autowired
    private ExceptionLog exceptionLog;

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/add")
    public void addUser(@RequestBody Client client, HttpServletResponse response) {
        try {
            clientList.registerUser(client);
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
    public List<Client> getUsers(HttpServletResponse response){
        try {
            response.setStatus(200);
            return clientList.getUsers();

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
            return clientList.isUserExist(email);

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
            return clientList.checkIfCanLogIn(email, password);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    @CrossOrigin(origins = ORIGIN)
    @PostMapping(value = "/buy")
    public void buyStock(@RequestBody Stock stock, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            Client client = clientList.getLoggedInUser();
            client.addToStock(stock);
            System.out.println(stock);



        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            exceptionLog.log(e);
            throw new IllegalArgumentException("Invalid user");

        } catch (NullPointerException e) {
            response.setStatus(400);
            throw new NullPointerException("User not created");
        }
    }
}
