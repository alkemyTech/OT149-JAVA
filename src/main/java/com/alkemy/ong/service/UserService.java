package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserPatchDTO;

public interface UserService {
  void userPatch(Long id, UserPatchDTO patchDto);
	void deleteUser(Long userId);
}