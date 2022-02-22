package com.alkemy.ong.service;

import com.alkemy.ong.model.User;

public interface UserService {

	public void deleteUser(Long userId);

	User save(User user);
}
