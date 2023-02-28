package com.ajayvaidya.user.service.UserServiceMicroservice.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Resource Not found in the server!!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
