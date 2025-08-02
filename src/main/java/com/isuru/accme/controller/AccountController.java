package com.isuru.accme.controller;

import com.isuru.accme.domain.dto.response.AccountResponseDto;
import com.isuru.accme.domain.dto.request.CreateAccountRequestDto;
import com.isuru.accme.domain.entity.AccountEntity;
import com.isuru.accme.exception.UserNotFoundException;
import com.isuru.accme.mapper.AccountMapper;
import com.isuru.accme.service.AccountService;
import com.isuru.accme.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final UserService userService;

    private final AccountService accountService;

    private final AccountMapper accountMapper;

    @PostMapping
    public ResponseEntity<AccountResponseDto> create(
            @RequestBody CreateAccountRequestDto createAccountRequestDto) {

        AccountEntity accountEntity = accountMapper.toAccountEntity(createAccountRequestDto);
        AccountEntity createdAccount = accountService.createAccount(accountEntity);
        AccountResponseDto accountResponseDto = accountMapper.toAccountDto(createdAccount);
        return new ResponseEntity<>(accountResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<AccountResponseDto>> getAccounts(
            @PathVariable String userId) throws UserNotFoundException {

        List<AccountResponseDto> accounts = accountService.getAccounts(userId).stream()
                .map(accountMapper::toAccountDto)
                .toList();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
