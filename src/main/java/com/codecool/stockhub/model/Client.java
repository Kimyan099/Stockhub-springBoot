package com.codecool.stockhub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@DynamicUpdate
public class Client {


    public static final double BALANCE = 5000;

    //@Column(nullable = false)
    private String name;

    //@Column(nullable = false)
    private String password;

    //@Column(unique = true, nullable = false)
    private String email;
    private double balance = BALANCE;

    private String address;
    private String phoneNumber;
    private String mobileNumber;

    @Id
    @GeneratedValue
    private Long id;

    @Singular
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    private Set<Stock> stocks;
}
