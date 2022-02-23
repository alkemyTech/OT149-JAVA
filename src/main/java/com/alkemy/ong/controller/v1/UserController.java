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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_PATCH_USERS;
import static com.alkemy.ong.controller.ControllerConstants.V_1_USERS;

@RestController
@RequestMapping(V_1_USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * Actualiza firstName, lastName y photo de users.
     *
     * @param id Id del usuario que se quiere actualizar.
     * @param patchDto Dto del usuario donde se encuentras los atributos que se pueden modificar.
     * @return Dto actualizado.
     * @throws Exception Devuelve un error 404.
     */
    @PatchMapping(V_1_PATCH_USERS)
    public ResponseEntity<?> userPatch(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserPatchDTO patchDto) throws Exception {

        try {
            UserPatchDTO response = service.userPatch(id, patchDto);
            return ResponseEntity.ok().body(response);
            //TODO falta la captura de errores de validacion
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
