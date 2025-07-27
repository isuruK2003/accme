package com.isuru.accme.service.impl;

import com.isuru.accme.domain.entity.UserEntity;
import com.isuru.accme.exception.UserNotFoundException;
import com.isuru.accme.repository.UserRepository;
import com.isuru.accme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getUser(String userId) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()){
            throw  new UserNotFoundException(userId);
        }
        return userEntity.get();
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Boolean isExists(String id) {
        return userRepository.existsById(id);
    }
}
