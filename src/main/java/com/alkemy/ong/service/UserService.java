package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserPatchDTO;

public interface UserService {

    UserPatchDTO userPatch(Long id, UserPatchDTO patchDto);
}
