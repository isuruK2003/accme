package com.isuru.accme.mapper;

import com.isuru.accme.domain.dto.response.UserResponseDto;
import com.isuru.accme.domain.dto.request.CreateUserRequestDto;
import com.isuru.accme.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDto toUserDto(UserEntity userEntity);

    UserEntity toUserEntity(CreateUserRequestDto createUserRequestDto);
}
