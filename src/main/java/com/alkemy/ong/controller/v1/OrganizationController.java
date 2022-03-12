package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.exception.ErrorDetails;
import com.alkemy.ong.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.alkemy.ong.controller.ControllerConstants.V_1_ORG;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_ORG)
public class OrganizationController {

    private final OrganizationService service;

    @Operation(summary = "Get an organization by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the organization",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Organization not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @GetMapping("/public/{id}")
    public  ResponseEntity<OrganizationResponseDto> getOrganization (@PathVariable(value="id") Long id){
        return ResponseEntity.ok().body(service.getOrganization(id));
    }

}
