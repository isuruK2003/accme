package com.isuru.accme.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    private String id;

    private String userId;

    private String accountId;

    private Long amount;
}