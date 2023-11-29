package com.airline.airplane_ms.model.enums;

import com.airline.common_exception.model.enums.CommonException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class Exceptions extends CommonException {
    public static final CommonException AIRPLANE_NOT_FOUND = new  CommonException("Airplane not found!",HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public Exceptions(String commonMessage, HttpStatus commonStatus, String status, HttpStatus httpStatus, LocalDateTime timestamp) {
        super(commonMessage, commonStatus);
        this.message = status;
        this.status = httpStatus;
        this.timestamp = timestamp;
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
