package com.alkemy.ong.controller.v1;

import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.controller.ControllerConstants.V_1_USERS;

@RestController
@RequestMapping(V_1_USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @DeleteMapping("{id}")
    @ResponseStatus( HttpStatus.NO_CONTENT)
    public void deleteUsers(@PathVariable Long userId){
        service.deleteUser(userId);
    }
}
