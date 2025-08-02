package com.isuru.accme.exception;

public class AccountNotFoundException extends IllegalArgumentException {

    public AccountNotFoundException(String accountId) {
        super(String.format("Account with id=%s is not available", accountId));
    }
}
