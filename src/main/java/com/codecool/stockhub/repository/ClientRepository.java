package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> getClientByEmailIs(String email);

    Optional<Client> findByName(String username);
}
