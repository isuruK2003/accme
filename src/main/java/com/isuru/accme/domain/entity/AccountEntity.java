package com.isuru.accme.domain.entity;

import com.isuru.accme.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class AccountEntity {

    @Id
    private String id;

    private String userId;

    private String name;

    private AccountType type;
}