package com.airline.common_exception.model.enums;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CommonException {
    public static final CommonException TOKEN_IS_INVALID_EXCEPTION = new CommonException("Token is invalid!",HttpStatus.BAD_REQUEST);
    public static final CommonException USER_IS_ALREADY_EXISTS =new CommonException("User is already exists!", HttpStatus.ALREADY_REPORTED);
    public static final CommonException TOKEN_IS_UNREACHABLE = new CommonException("Token is unreachable!", HttpStatus.NOT_FOUND);
    public static final CommonException USERNAME_NOT_FOUND = new CommonException("Username not found!",HttpStatus.NOT_FOUND);
    public static final CommonException USER_NOT_FOUND = new CommonException("User not found!",HttpStatus.NOT_FOUND );
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public CommonException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now().withNano(0);
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message: " + message +
                "\nStatus: " + status +
                "\nTimestamp: " + timestamp;
    }
}
