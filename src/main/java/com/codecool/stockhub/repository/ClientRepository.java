package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.UserObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserObject, Long> {
}
