package com.alkemy.ong.controller.v1;

import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.controller.ControllerConstants.V_1_USERS;

@RestController
@RequestMapping(V_1_USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

}
