package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.dto.UserPatchDTO;

public interface UserService {
  UserResponseDto saveUser(UserDto user);
  void userPatch(Long id, UserPatchDTO patchDto);
	void deleteUser(Long userId);
}