package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import static com.alkemy.ong.controller.ControllerConstants.REQ_MAPP_PUBLIC;
import static com.alkemy.ong.controller.ControllerConstants.V_1_ORG;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_ORG)
public class OrganizationController {

    private final OrganizationService service;

    @GetMapping(REQ_MAPP_PUBLIC)
    public  ResponseEntity<OrganizationResponseDto> getOrganization (@PathVariable(value="id") Long id){
        return ResponseEntity.ok().body(service.getOrganization(id));
    }

}
