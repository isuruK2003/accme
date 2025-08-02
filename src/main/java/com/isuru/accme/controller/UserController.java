package com.isuru.accme.controller;

import com.isuru.accme.domain.dto.request.CreateUserRequestDto;
import com.isuru.accme.domain.dto.response.UserResponseDto;
import com.isuru.accme.domain.entity.UserEntity;
import com.isuru.accme.exception.UserNotFoundException;
import com.isuru.accme.mapper.UserMapper;
import com.isuru.accme.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserEntity userEntity = userMapper.toUserEntity(createUserRequestDto);
        log.info(createUserRequestDto.toString());
        log.info(userEntity.toString());
        UserEntity createdUser = userService.createUser(userEntity);
        UserResponseDto userResponseDto = userMapper.toUserDto(createdUser);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String userId) throws UserNotFoundException {
        UserEntity userEntity = userService.getUser(userId);
        UserResponseDto userResponseDto = userMapper.toUserDto(userEntity);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
