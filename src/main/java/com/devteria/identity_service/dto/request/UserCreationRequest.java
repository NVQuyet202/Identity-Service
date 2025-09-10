package com.devteria.identity_service.dto.request;

import com.devteria.identity_service.exception.ErrorCode;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data //Anotation giúp tạo các hàm getter setter
@NoArgsConstructor // khởi tạo contrucor no Args
@AllArgsConstructor // Khởi tạo contructor
@Builder //giúp ta tạo ra builder class cho dto
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, max = 50,message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;


}
