package com.codecool.stockhub.model;

import java.util.UUID;

public class User {

    public static final double BALANCE = 5000;
    private String name;
    private UUID uuid;
    private String password;
    private String email;
    private double balance;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.uuid = UUID.randomUUID();
        this.balance = BALANCE;
    }

    public User(){
        this.uuid = UUID.randomUUID();
    }


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
