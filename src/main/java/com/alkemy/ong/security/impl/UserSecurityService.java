package com.alkemy.ong.security.impl;

import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
