package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.OrganizationPutDto;
import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.exception.ErrorDetails;
import com.alkemy.ong.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
public class OrganizationController {

    private final OrganizationService service;

    @Operation(summary = "Get an organization by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the organization",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationResponseDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Organization not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @GetMapping("/public/{id}")
    public  ResponseEntity<OrganizationResponseDto> getOrganization (@PathVariable(value="id") Long id){
        return ResponseEntity.ok().body(service.getOrganization(id));
    }

    @Operation(summary = "Update organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update organization",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PutMapping("/public/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable Long id,
                                                @Valid @RequestBody OrganizationPutDto organization){
        service.updateOrganization(id, organization);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
