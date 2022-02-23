package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UsersRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserMapper userMapper;
    @Override
    public UserPatchDTO userPatch(Long id, UserPatchDTO patchDto) {

        User user = usersRepository.findById(id).orElse(null);
        if (user==null){
            throw new  UserNotFoundException("No existe un usuario con id "+id);
        }
        user.setFirstName(patchDto.getFirstName());
        user.setLastName(patchDto.getLastName());
        user.setPhoto(patchDto.getPhoto());
        user=usersRepository.save(user);
        UserPatchDTO response = userMapper.toUserPatchDTO(user);
        return response;
    }
}
