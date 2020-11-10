package com.codecool.stockhub.model;

import com.codecool.stockhub.repository.StockRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {


    public static final double BALANCE = 5000;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;
    private double balance = BALANCE;

    @Id
    @GeneratedValue
    private Long id;

    @Singular
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Stock> stocks;

    public void addToStock(Stock stock) {
        stocks.add(stock);
        System.out.println(stocks);
    }
}
