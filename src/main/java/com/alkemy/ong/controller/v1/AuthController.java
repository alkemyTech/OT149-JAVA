package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.model.User;
import com.alkemy.ong.service.AuthService;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserDto user) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));

    }

    @GetMapping("/me")
    public ResponseEntity<User> userLogged()throws UserNotFoundException {
        return new ResponseEntity<>(authService.getUserLogged(), HttpStatus.OK);
    }


}
