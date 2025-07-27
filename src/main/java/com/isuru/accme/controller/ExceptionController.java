package com.isuru.accme.controller;

import com.isuru.accme.domain.dto.response.ExceptionResponse;
import com.isuru.accme.domain.dto.response.InvalidRequestExceptionResponse;
import com.isuru.accme.exception.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        log.error("Exception caught: ",exception);
        ExceptionResponse response = ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred")
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        ExceptionResponse response = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(illegalArgumentException.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<InvalidRequestExceptionResponse> handleInvalidRequestExceptionResponse(InvalidRequestException invalidRequestException) {
        InvalidRequestExceptionResponse response = InvalidRequestExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(invalidRequestException.getMessage())
                .fieldErrors(invalidRequestException.getFieldErrors())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}