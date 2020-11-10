package com.codecool.stockhub.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    private double price;

    //@Column(unique = true, nullable = false)
    private String symbol;

    private String name;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Client client;
}