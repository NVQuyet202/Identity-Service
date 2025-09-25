package com.devteria.identity_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data //Anotation giúp tạo các hàm getter setter
@NoArgsConstructor // khởi tạo contrucor no Args
@AllArgsConstructor // Khởi tạo contructor
@Builder //giúp ta tạo ra builder class cho dto
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    String name;
    String description;
    Set<String> permissions;

}
