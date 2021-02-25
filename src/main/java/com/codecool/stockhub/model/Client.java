package com.codecool.stockhub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table
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

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();
}
