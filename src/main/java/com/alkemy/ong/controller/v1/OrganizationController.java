package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.OrganizationPutDto;
import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_ORG;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_ORG)
public class OrganizationController {

    private final OrganizationService service;

    @GetMapping("/public/{id}")
    public  ResponseEntity<OrganizationResponseDto> getOrganization (@PathVariable(value="id") Long id){
        return ResponseEntity.ok().body(service.getOrganization(id));
    }

    @PutMapping("/public/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable Long id,
                                                @Valid @RequestBody OrganizationPutDto organization){
        service.updateOrganization(id, organization);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
