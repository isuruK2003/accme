package com.isuru.accme.exception;

public class TransactionNotFoundException extends IllegalArgumentException {

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
