package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_AUTH;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_AUTH)
public class AuthController {

    private final UserService usersService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserDto user){

        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.saveUser(user));

    }


}
