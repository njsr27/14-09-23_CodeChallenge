package com.example.demo.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("User with username " + username + " already exists.");
    }

    public UserAlreadyExistsException(StringBuilder usernames) {
        super("Users with usernames " + usernames + " already exist.");
    }
}
