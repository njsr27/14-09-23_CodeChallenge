package com.example.demo.exception;

public class InvalidGenderException extends IllegalArgumentException {

    public InvalidGenderException(String message) {
        super(message);
    }
}
