package com.codecool.stockhub.model;

import lombok.*;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Stock> stocks;

}
