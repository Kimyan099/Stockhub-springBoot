package com.codecool.stockhub.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    public static final int BALANCE = 5000;
    private String name;
    private UUID uuid;
    private String password;
    private String email;
    private final Map<Stock, Integer> stocks = new HashMap<>();
    private double balance;

    public User(String name, String password, String email) {
        System.out.println(name);
        System.out.println(password);
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.password = password;
        this.email = email;
        this.balance = BALANCE;
    }

//    public User(){
//
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void buyStock(Stock stock, int amount) {
        balance -= stock.getPrice() * amount;
        stocks.put(stock, amount);
    }

    public void sellStock(Stock stock, int amount) {
        balance += stock.getPrice() * amount;
        stocks.remove(stock, amount);
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

    public Map<Stock, Integer> getStocks() {
        return stocks;
    }

    public double getBalance() {
        return balance;
    }


}
