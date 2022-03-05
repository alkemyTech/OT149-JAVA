package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.model.User;
import com.alkemy.ong.payload.request.RegisterRequest;

import java.util.Optional;

public interface UserService {

	UserResponseDto saveUser(RegisterRequest registerRequest);
    void userPatch(Long id, UserPatchDTO patchDto);
	void deleteUser(Long userId);
	Optional<User> findUserByEmail(String email);
}