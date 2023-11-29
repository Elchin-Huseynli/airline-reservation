package com.airline.common_exception.exception;


import com.airline.common_exception.model.enums.CommonException;

public class ApplicationException extends RuntimeException {
    private final CommonException exceptions;
    public ApplicationException(CommonException exceptions) {
        super(exceptions.toString());
        this.exceptions = exceptions;
    }

    public CommonException getExceptions() {
        return exceptions;
    }

}

