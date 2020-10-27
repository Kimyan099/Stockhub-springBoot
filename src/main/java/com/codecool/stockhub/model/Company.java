package com.codecool.stockhub.model;

public class Company {

    private String description;
    private String symbol;

    public Company(String description, String symbol) {
        this.description = description;
        this.symbol = symbol;
    }

    public Company() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
