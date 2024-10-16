package com.devteria.identity_service.exception;

public enum ErrorCode {
    //define errorcode không xác định
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    //define error khi lỗi code
    INVALID_KEY(1001, "Invalid message key"),
    //define errorcode cho user exist
    USER_EXISTED(1002, "User already existed"),
    //Define error khi nằm ngoài validation
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters"),
    USER_NOT_EXISTED(1005, "User not existed"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
