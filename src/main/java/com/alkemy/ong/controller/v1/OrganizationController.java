package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.OrganizationPutDto;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_ORGANIZATIONS;

@RestController
@RequestMapping(V_1_ORGANIZATIONS)
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService service;

    @PutMapping("/public/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateOrganization(@PathVariable Long id, @Valid @RequestBody OrganizationPutDto organization){
        service.updateOrganization(id, organization);
    }
}
