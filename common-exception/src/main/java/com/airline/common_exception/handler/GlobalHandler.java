package com.airline.common_exception.handler;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_exception.util.MessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalHandler extends DefaultErrorAttributes {

    private final MessagesUtil messagesUtil;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handle(ApplicationException ex,
                                                      WebRequest request) {

        return errorAttributes(ex, request);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handlerException(@NotNull Exception ex,
                                                   WebRequest request) {

        return errorAttributes(ex, request);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handle(MethodArgumentNotValidException ex,
                                                      WebRequest request) {

        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        Map<String, Object> invalidFields = new HashMap<>();

        ex.getFieldErrors()
                .forEach(fieldError -> invalidFields.put(fieldError.getField(),
                        messagesUtil.getMessage(fieldError.getDefaultMessage())));

        errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
        errorAttributes.put("error", invalidFields);
        errorAttributes.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
    }



    private ResponseEntity<Map<String, Object>> errorAttributes(Throwable ex,WebRequest request){

        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
        errorAttributes.put("error", messagesUtil.getMessage(ex.getMessage()));
        errorAttributes.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);

    }


}
