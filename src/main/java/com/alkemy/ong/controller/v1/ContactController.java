package com.alkemy.ong.controller.v1;

import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.alkemy.ong.controller.ControllerConstants.V_1_CONTACTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_CONTACTS)
public class ContactController {

    private final ContactService contactService;


}
