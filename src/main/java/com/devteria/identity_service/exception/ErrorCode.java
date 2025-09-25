package com.devteria.identity_service.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGOIED_EXCEPTION(9999,"Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(1003, "Username must be at least 3 characters",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1001,"Invalid message key", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002,"User Existed", HttpStatus.BAD_REQUEST),
    USER_DOESNT_EXISTED(1005,"User doesn't exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006,"Access Denied", HttpStatus.FORBIDDEN);

     int code ;
     String message;
     HttpStatusCode statusCode;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
