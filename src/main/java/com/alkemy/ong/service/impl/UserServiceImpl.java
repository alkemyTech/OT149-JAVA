package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.RegisterRequest;
import com.alkemy.ong.dto.UserPagedList;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.mail.EmailService;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.JwtUtils;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoto(), user.getCreatedDate(), jwt);
    }

    @Transactional(readOnly = true)
    @Override
    public UserPagedList pagedList(PageRequest pageRequest) {

        Page<User> pageUser = userRepository.findAll(pageRequest);

        final List<UserPatchDTO> list = pageUser.getContent().stream().map(userMapper::toUserPatchDTO).collect(Collectors.toList());
        final PageRequest of = PageRequest.of(pageUser.getPageable().getPageNumber(), pageUser.getPageable().getPageSize());
        final long totalElements = pageUser.getTotalElements();

        return new UserPagedList(list, of, totalElements);
    }

    @Transactional
    @Override
    public UserResponseDto saveUser(RegisterRequest registerRequest) {

        User newUser = userMapper.toUser(registerRequest);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        emailService.sendWelcomeEmail(newUser.getEmail());

        return registerResponse(userRepository.save(newUser), getToken(newUser));
    }

    /**
     * Este método guarda los cambio en la base de datos.
     *
     * @param id       Id del User a patchear.
     * @param patchDto Dto del User modificado.
     */
    @Transactional
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

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(userRepository::delete);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
