package com.airline.flight_ms.model.enums;

import com.airline.common_exception.model.enums.CommonException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;


public class Exceptions extends CommonException {

    public static final Exceptions FLIGHT_NOT_FOUND = new Exceptions("Flight not found!", HttpStatus.NOT_FOUND);
    public static final Exceptions FROM_ID_AND_TO_ID_EQUALS = new Exceptions("From id and to id is equal!", HttpStatus.NOT_FOUND);
    public static final Exceptions SEATS_ARE_SOLD_OUT = new Exceptions("Seats are sold out!", HttpStatus.NOT_FOUND);
    public static final Exceptions FLIGHT_DELETE_UNSUCCESSFULLY = new Exceptions( "Flight delete unsuccessfully", HttpStatus.BAD_REQUEST) ;
    public static final Exceptions PLANE_BUSY = new Exceptions( "The plane is busy at this time",HttpStatus.BAD_REQUEST);
    public static final Exceptions ILLEGAL_DATE_TIME = new Exceptions("The current date does not match the future date!",HttpStatus.BAD_REQUEST);

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
