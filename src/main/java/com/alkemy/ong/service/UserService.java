package com.alkemy.ong.service;

import com.alkemy.ong.dto.RegisterRequest;
import com.alkemy.ong.dto.UserPagedList;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.model.User;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface UserService {
	UserResponseDto saveUser(RegisterRequest registerRequest);
    void userPatch(Long id, UserPatchDTO patchDto);
	void deleteUser(Long userId);
	Optional<User> findUserByEmail(String email);
	UserPagedList pagedList(PageRequest pageRequest);
}
