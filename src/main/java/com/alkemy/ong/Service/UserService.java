package com.alkemy.ong.Service;

import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User checkPass(String password, String username) {
        if (username == null || username.isEmpty()) {
            throw new InvalidPropertyException(null, username, "Debe rellenar el campo de nombre de usuario");
        }
        if (password == null || password.isEmpty()) {
            throw new InvalidPropertyException(null, password, "Debe rellenar el campo de contraseña de usuario");
        }

        Optional<User> response = userRepository.findByMail(username);

        if (response.isPresent()) {
            User user = response.get();
            if (passwordEncoder.matches(user.getPassword(), password)) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


}
