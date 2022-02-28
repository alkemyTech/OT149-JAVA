package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UsersRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private final UsersRepository usersRepository;

	@Autowired
	public UserServiceImpl(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	@Transactional
	public void deleteUser(Long userId){
		User user = usersRepository.getById(userId);
		usersRepository.delete(user);
	}

}
