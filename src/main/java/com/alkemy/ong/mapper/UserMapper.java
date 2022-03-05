package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.model.User;
import com.alkemy.ong.payload.request.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(RegisterRequest dto);

    UserDto toDto(User user);

    UserPatchDTO toUserPatchDTO(User user);
}
