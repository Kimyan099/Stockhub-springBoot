package com.codecool.security.repository;


import com.codecool.security.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> getClientByEmail(String email);

    Optional<Client> findByName(String username);
}
