package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Annotation giúp tạo cái bean repository
@Repository
public interface PermissionRepository extends JpaRepository <Permission, String> {
}
