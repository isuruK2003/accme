package com.isuru.accme.controller;

import com.isuru.accme.domain.dto.CreateUserRequest;
import com.isuru.accme.domain.dto.UserDto;
import com.isuru.accme.domain.entity.UserEntity;
import com.isuru.accme.exception.UserNotFoundException;
import com.isuru.accme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        UserEntity userEntity = UserEntity.builder()
                .fName(createUserRequest.getFName())
                .lName(createUserRequest.getLName())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .build();
        UserEntity createdUser = userService.createUser(userEntity);
        UserDto userDto = UserDto.builder()
                .id(createdUser.getId())
                .fName(createdUser.getFName())
                .lName(createdUser.getLName())
                .email(createdUser.getEmail())
                .build();
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) throws UserNotFoundException {
        UserEntity userEntity = userService.getUser(userId);
        UserDto userDto = UserDto.builder()
                .fName(userEntity.getFName())
                .lName(userEntity.getLName())
                .email(userEntity.getEmail())
                .build();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
