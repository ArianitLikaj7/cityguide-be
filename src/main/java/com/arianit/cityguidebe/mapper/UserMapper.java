package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.UserRequest;
import com.arianit.cityguidebe.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements GenericMapper<User, UserDto, UserRequest>{

    private final ModelMapper modelMapper;

    @Override
    public UserDto toDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public User toEntity(UserRequest request) {
        return modelMapper.map(request, User.class);
    }
}
