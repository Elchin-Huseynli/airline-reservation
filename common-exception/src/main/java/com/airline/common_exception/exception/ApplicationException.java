package com.airline.common_exception.exception;


public class ApplicationException extends RuntimeException {
    private final String exceptions;
    public ApplicationException(String exceptions) {
        super(exceptions);
        this.exceptions = exceptions;
    }

    public String getMessage() {

        return exceptions;
    }



}

