package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.mapper.UserResponseMapper;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public UserResponseDto saveUser(UserDto userDTO) {

        User newUser = userMapper.toUser(userDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userResponseMapper.toUserResponse(userRepository.save(newUser));
    }
}
