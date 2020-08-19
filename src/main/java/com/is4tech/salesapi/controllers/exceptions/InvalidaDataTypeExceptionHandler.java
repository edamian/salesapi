package com.is4tech.salesapi.controllers.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InvalidaDataTypeExceptionHandler {
    @ExceptionHandler(InvalidFormatException.class)
    protected ResponseEntity handleInvalidDataTypeExceptions(InvalidFormatException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Invalid data type");
        ex.printStackTrace();
        return  new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
}
