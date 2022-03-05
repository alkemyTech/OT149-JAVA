package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.mail.EmailService;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.User;
import com.alkemy.ong.payload.request.RegisterRequest;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.JwtUtils;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final JwtUtils jwtUtils;

    private String getToken(User user) {
        return jwtUtils.generateToken(user);
    }

    private UserResponseDto registerResponse(User user, String jwt) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoto(),
                user.getCreatedDate(),
                jwt);
    }

    @Override
    public UserResponseDto saveUser(RegisterRequest registerRequest) {

        User newUser = userMapper.toUser(registerRequest);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        //emailService.sendWelcomeEmail(newUser.getEmail());

        return registerResponse(userRepository.save(newUser), getToken(newUser));
    }

    /**
     * Este método guarda los cambio en la base de datos.
     *
     * @param id       Id del User a patchear.
     * @param patchDto Dto del User modificado.
     */
    @Override
    public void userPatch(Long id, UserPatchDTO patchDto) {

        userRepository.findById(id).map(user -> {

            user.setFirstName(patchDto.getFirstName());
            user.setLastName(patchDto.getLastName());
            user.setPhoto(patchDto.getPhoto());

            return userRepository.save(user);

        }).orElseThrow(() -> {

            throw new UserNotFoundException();

        });
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.getById(userId);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}