package com.devteria.identity_service.exception;

public enum ErrorCode {
    UNCATEGOIED_EXCEPTION(9999,"Uncategorized error"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    INVALID_KEY(1001,"Invalid message key"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    USER_EXISTED(1002,"User Existed"),
    USER_DOESNT_EXISTED(1005,"User doesn't exist"),
    UNAUTHENTICATED(1006,"Unauthenticated");
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
