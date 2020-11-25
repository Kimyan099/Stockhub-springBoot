package com.codecool.stockhub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockCredential {
    String symbol;
    String name;
    Double price;
    String imageLink;
}
