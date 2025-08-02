package com.isuru.accme.domain.dto.response;

import com.isuru.accme.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {

    private String id;

    private String userId;

    private String name;

    private AccountType type;
}