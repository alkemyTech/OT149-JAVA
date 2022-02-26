package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_USERS;

@RestController
@RequestMapping(V_1_USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * Este método modifica los campos firstName, lastName y photo de User.
     * @param id Id del User a patchear.
     * @param patchDto Dto con los cambios a realizar.
     */
    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void userPatch(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserPatchDTO patchDto){
            service.userPatch(id, patchDto);
    }
}
