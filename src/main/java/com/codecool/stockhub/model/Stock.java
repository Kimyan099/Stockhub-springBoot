package com.codecool.stockhub.model;

public class Stock {

    private double price;
    private String symbol;
    private String name;


    public Stock(double price, String symbol, String name) {
        this.price = price;
        this.symbol = symbol;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}