package com.devteria.identity_service.dto.request;

import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data //Anotation giúp tạo các hàm getter setter
@NoArgsConstructor // khởi tạo contrucor no Args
@AllArgsConstructor // Khởi tạo contructor
@Builder //giúp ta tạo ra builder class cho dto
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, max = 50,message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;

}
