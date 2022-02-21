package com.alkemy.ong.service.impl;

import com.alkemy.ong.repository.UsersRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UsersRepository usersRepository;

	@Autowired
	public UserServiceImpl(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public void deleteUser(Long userId){
		Users user = usersRepository.getById(userId);
		usersRepository.delete(user);
	}
}
