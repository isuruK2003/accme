package com.isuru.accme.service;

import com.isuru.accme.domain.entity.UserEntity;
import com.isuru.accme.exception.UserNotFoundException;

public interface UserService {

    UserEntity getUser(String id) throws UserNotFoundException;

    UserEntity createUser(UserEntity userEntity);

    Boolean isExists(String id);
}
