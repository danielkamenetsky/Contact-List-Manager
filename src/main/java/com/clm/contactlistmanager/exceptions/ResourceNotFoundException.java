package com.clm.contactlistmanager.exceptions;

// This is our custom exception when a resource (like a Contact or Note) is not found.
public class ResourceNotFoundException extends RuntimeException {

    // Constructor that accepts a message about what went wrong.
    public ResourceNotFoundException(String message) {
        super(message);  // Pass the message up to the RuntimeException class.
    }
}
