package com.isuru.accme.exception;

public class TransactionNotFoundException extends IllegalArgumentException {

    public TransactionNotFoundException(String transactionId) {
        super(String.format("Entry with transactionId=%s cannot be found", transactionId));
    }
}
