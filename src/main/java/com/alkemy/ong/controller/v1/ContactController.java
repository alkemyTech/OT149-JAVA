package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.alkemy.ong.controller.ControllerConstants.V_1_CONTACTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_CONTACTS)
public class ContactController {

    private final ContactService contactService;

    /**
     * This endpoint gets all the active contacts saved in the database. Only can be used by Admin
     * @return The contacts list as List<ContactDto>
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ContactDto>> getAll(){
        List<ContactDto> dtoList= this.contactService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

}
