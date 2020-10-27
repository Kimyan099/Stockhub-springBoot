package com.codecool.stockhub.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    private String name;
    private UUID uuid = UUID.randomUUID();
    private String password;
    private String email;

    private final double balance = 5000;

    public User(String name, String password, String email) {
        System.out.println(name);
        System.out.println(password);
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }


    public UUID getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }


}
