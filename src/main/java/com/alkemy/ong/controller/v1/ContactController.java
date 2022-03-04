package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_CONTACTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_CONTACTS)
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@Valid @RequestBody ContactDto dto) {
        ContactDto result = this.contactService.saveContact(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
