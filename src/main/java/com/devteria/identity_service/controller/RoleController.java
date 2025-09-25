package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.ApiResponse;
import com.devteria.identity_service.dto.request.PermissionCreationRequest;
import com.devteria.identity_service.dto.request.RoleRequest;
import com.devteria.identity_service.dto.response.PermissionResponse;
import com.devteria.identity_service.dto.response.RoleResponse;
import com.devteria.identity_service.service.PermissionService;
import com.devteria.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .build();
    }
}
