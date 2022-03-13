package com.alkemy.ong.service;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;


public interface AuthService {

	UserDto getUserLogged();

	AuthenticationResponse signIn(AuthenticationRequest authRequest);
}
