package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UsersRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserMapper userMapper;

    /**
     * Este método guarda los cambio en la base de datos.
     * @param id Id del User a patchear.
     * @param patchDto Dto del User modificado.
     */
    @Override
    public void userPatch(Long id, UserPatchDTO patchDto) {
        usersRepository.findById(id).map(user -> {

            user.setFirstName(patchDto.getFirstName());
            user.setLastName(patchDto.getLastName());
            user.setPhoto(patchDto.getPhoto());

            return usersRepository.save(user);


        }).orElseThrow(() -> {

            throw new UserNotFoundException();

        });
   }

	@Override
	@Transactional
	public void deleteUser(Long userId){
		User user = usersRepository.getById(userId);
		usersRepository.delete(user);
	}

}
