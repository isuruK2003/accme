package com.isuru.accme.exception;

import lombok.*;

import java.util.List;

public class InvalidRequestException extends IllegalArgumentException {

    @Getter
    private final List<FieldError> fieldErrors;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FieldError {
        private String field;
        private String message;
    }

    public InvalidRequestException(String message, List<FieldError> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }
}
