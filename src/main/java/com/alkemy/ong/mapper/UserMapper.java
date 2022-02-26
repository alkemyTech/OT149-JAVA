package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(UserDto dto);

    UserDto toDto(User user);
}
