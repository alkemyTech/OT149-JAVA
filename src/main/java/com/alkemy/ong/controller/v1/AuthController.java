package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.dto.RegisterRequest;
import com.alkemy.ong.service.AuthService;
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
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody RegisterRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> userLogged() {
        return new ResponseEntity<>(authService.getUserLogged(), HttpStatus.OK);
    }

    /**
     * This endpoint allows the user to log in by entering their email and password to authenticate.
     * Email and password are validated. In case the login is incorrect, the response will be {ok: false}
     *
     * @param authRequest The authentication request containing the user's email and password
     * @return The authentication response containing the user's jwt token
     */

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> signIn(@Valid @RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(this.authService.signIn(authRequest));
    }
}
