package com.clm.contactlistmanager.exceptions;

// This is our custom exception when the input data is invalid.
public class InvalidInputException extends RuntimeException {

    // Constructor that accepts a message about what went wrong.
    public InvalidInputException(String message) {
        super(message);  // Pass the message up to the RuntimeException class.
    }
}
