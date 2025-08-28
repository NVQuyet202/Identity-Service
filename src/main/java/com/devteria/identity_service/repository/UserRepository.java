package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Annotation giúp tạo cái bean repository
@Repository
public interface UserRepository extends JpaRepository <User, String> {
    Boolean existsByUsername(String username);
}
