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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_CONTACTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_CONTACTS)
public class ContactController {

    private final ContactService contactService;

    /**
     * This endpoint creates a new contact to save to the database. Input fields are validated.
     *
     * @param dto The new contact to save as ContactDto
     * @return The contact saved as ContactDto
     */

    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@Valid @RequestBody ContactDto dto) {
        ContactDto result = this.contactService.saveContact(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    /**
     * This endpoint gets all the active contacts saved in the database. Only can be used by Admin
     *
     * @return The contacts list as List<ContactDto>
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ContactDto>> getAll() {
        List<ContactDto> dtoList = this.contactService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }


}