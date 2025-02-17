package com.example.expconv_server.web.mappers;

import com.example.expconv_server.domain.user.User;
import com.example.expconv_server.domain.user.User;
import com.example.expconv_server.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
