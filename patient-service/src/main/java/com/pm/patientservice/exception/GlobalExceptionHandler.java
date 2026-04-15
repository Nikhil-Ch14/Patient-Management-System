package com.pm.patientservice.exception;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    //gets triggered when the input validation is done by jpa on the requestdtos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        //this method is going to return a map , methodarguementnotvalidexception catches any errors in the request
       Map<String,String> errors=new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(
               error-> errors.put(error.getField(),error.getDefaultMessage()));
       return ResponseEntity.badRequest().body(errors);
    }


}
