package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.mapper.UserResponseMapper;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public UserResponseDto saveUser(UserDto userDTO) {

        User newUser = userMapper.toUser(userDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userResponseMapper.toUserResponse(userRepository.save(newUser));
    }

    /**
     * Este método guarda los cambio en la base de datos.
     * @param id Id del User a patchear.
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
	public void deleteUser(Long userId){
		User user = userRepository.getById(userId);
		userRepository.delete(user);
	}

}
