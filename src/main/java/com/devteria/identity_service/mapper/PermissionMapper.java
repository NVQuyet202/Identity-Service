package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.PermissionCreationRequest;
import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.PermissionResponse;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.Permission;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.repository.PermissionRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
