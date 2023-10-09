package com.clm.contactlistmanager.controllers;

import com.clm.contactlistmanager.exceptions.InvalidInputException;
import com.clm.contactlistmanager.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  // This means this class gives advice to all controllers about exceptions.
public class GlobalExceptionHandler {

    // If a ResourceNotFoundException gets thrown anywhere in our app, this method handles it.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        // Return a NOT FOUND status and the message from the exception.
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // If an InvalidInputException gets thrown, this method handles it.
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException e) {
        // Return a BAD REQUEST status and the message from the exception.
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // For other unexpected exceptions, this method will handle them.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        // Return an INTERNAL SERVER ERROR status and the exception's message.
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
