package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserResponseMapper {

   UserResponseDto toUserResponse(User user);
    User toUser(UserResponseDto userResponse);

}
