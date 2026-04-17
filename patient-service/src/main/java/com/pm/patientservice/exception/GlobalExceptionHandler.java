package com.pm.patientservice.exception;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //gets triggered when the input validation is done by jpa on the requestdtos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        //this method is going to return a map , methodarguementnotvalidexception catches any errors in the request
       Map<String,String> errors=new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(
               error-> errors.put(error.getField(),error.getDefaultMessage()));
       return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailLAlreadyExistsException(EmailAlreadyExistsException ex){
      log.warn("email address already exixts {}" , ex.getMessage());
        Map<String,String> errors=new HashMap<>();
        errors.put("message" , "Email address already exists");
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotFoundException(
            PatientNotFoundException ex) {
        log.warn("Patient not found {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Patient not found");
        return ResponseEntity.badRequest().body(errors);
    }
}
