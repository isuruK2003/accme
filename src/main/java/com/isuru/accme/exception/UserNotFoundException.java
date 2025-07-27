package com.isuru.accme.exception;

public class UserNotFoundException extends IllegalArgumentException {

    public UserNotFoundException(String userId) {
        super(String.format("User with id=%s is not available", userId));
    }
}
