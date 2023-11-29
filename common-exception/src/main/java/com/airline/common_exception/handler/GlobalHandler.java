package com.airline.common_exception.handler;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_exception.model.dto.response.ExceptionResponse;
import com.airline.common_exception.model.enums.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.constraints.NotNull;


@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handlerApplicationException(@NotNull ApplicationException exception) {
        CommonException commonExceptions =  exception.getExceptions();

        return new ResponseEntity<>(commonExceptions.toString(),commonExceptions.getStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(@NotNull Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> handlerValidationException(@NotNull BindException exception) {
        FieldError fieldError = exception.getFieldError();

        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "Validation error occurred!";

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.of(errorMessage, HttpStatus.BAD_REQUEST));

    }


}
