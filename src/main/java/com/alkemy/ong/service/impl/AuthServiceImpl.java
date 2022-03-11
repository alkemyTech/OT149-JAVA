package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.exception.BadUserLoginException;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.JwtUtils;
import com.alkemy.ong.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private JwtUtils jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDto getUserLogged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return mapper.toDto(usersRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                }));
    }

    @Override
    public AuthenticationResponse signIn(AuthenticationRequest authRequest) {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (Exception e) {
            throw new BadUserLoginException("There is a problem at login");
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);

    }
}
