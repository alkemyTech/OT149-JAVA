package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserResponseDto;


public interface UserService {
    UserResponseDto saveUser(UserDto user);
}
