package com.isuru.accme.domain.dto.response;

import com.isuru.accme.exception.InvalidRequestException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidRequestExceptionResponse {

    private Integer status;
    private String message;
    private List<InvalidRequestException.FieldError> fieldErrors;
}