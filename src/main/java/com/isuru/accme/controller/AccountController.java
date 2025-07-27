package com.isuru.accme.controller;

import com.isuru.accme.domain.dto.AccountDto;
import com.isuru.accme.domain.dto.request.CreateAccountRequest;
import com.isuru.accme.domain.entity.AccountEntity;
import com.isuru.accme.exception.UserNotFoundException;
import com.isuru.accme.service.AccountService;
import com.isuru.accme.service.UserService;
import com.isuru.accme.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final UserService userService;

    private final AccountService accountService;
    private final AccountServiceImpl accountServiceImpl;

    @GetMapping("{userId}")
    public ResponseEntity<List<AccountDto>> getAccounts(
            @PathVariable String userId) throws UserNotFoundException {
        if (!userService.isExists(userId)) {
            throw new UserNotFoundException(userId);
        }
        List<AccountDto> accounts = accountService.getAccounts(userId).stream()
                .map(accountEntity -> AccountDto.builder()
                        .id(accountEntity.getId())
                        .userId(accountEntity.getUserId())
                        .name(accountEntity.getName())
                        .type(accountEntity.getType())
                        .build())
                .toList();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> create(
            @RequestBody CreateAccountRequest createAccountRequest) {
        if (!userService.isExists(createAccountRequest.getUserId())) {
            throw new UserNotFoundException(createAccountRequest.getUserId());
        }
        AccountEntity accountEntity = AccountEntity.builder()
                .userId(createAccountRequest.getUserId())
                .name(createAccountRequest.getName())
                .type(createAccountRequest.getType())
                .build();
        AccountEntity createdAccount = accountService.createAccount(accountEntity);
        AccountDto accountDto = AccountDto.builder()
                .id(createdAccount.getId())
                .userId(createdAccount.getUserId())
                .name(createdAccount.getName())
                .type(createdAccount.getType())
                .build();
        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }
}
