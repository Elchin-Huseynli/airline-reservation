package com.airline.booking_ms.model.enums;


import com.airline.common_exception.model.enums.CommonException;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;

public class Exceptions extends CommonException {

    public static final Exceptions TICKET_NOT_FOUND = new Exceptions("Ticket not found!", HttpStatus.NOT_FOUND);
    public static final Exceptions FROM_SOURCE_AND_TO_DESTINATION_EQUALS = new Exceptions("Source and destination is equal location.", HttpStatus.NOT_FOUND);
    public static final Exceptions ILLEGAL_DATE_TIME = new Exceptions("The current date does not match the future date!", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp = now();


    public Exceptions(String message, HttpStatus status) {
        super(message, status);
        this.message = message;
        this.status = status;
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
