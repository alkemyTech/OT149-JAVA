package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.model.User;
import com.alkemy.ong.dto.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface UserMapper {
    User toUser(RegisterRequest dto);

    UserDto toDto(User user);

    UserPatchDTO toUserPatchDTO(User user);

    @Mapping(target = "password", expression = "java(null)")
    @Named("reduced")
    UserDto toDtoReduced(User user);
}
