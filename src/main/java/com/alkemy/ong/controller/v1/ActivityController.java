package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.ActivityDto;

import com.alkemy.ong.exception.ErrorDetails;
import com.alkemy.ong.service.impl.ActivityServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.REQ_MAPP_ACTIVITIES;

@RestController
@RequiredArgsConstructor
@RequestMapping(REQ_MAPP_ACTIVITIES)
@SecurityRequirement(name = "bearerAuth")
public class ActivityController {

    @Autowired
    private ActivityServiceImp activityServiceImp;

    @Operation(summary = "Add a new activity to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create Activity",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))})
             })
    @PostMapping()
    public ResponseEntity<Void> addActivity(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody ActivityDto dto) {

        final long activityId = activityServiceImp.saveActivity(dto);

        UriComponents uriComponents = uriComponentsBuilder.path(REQ_MAPP_ACTIVITIES + "/{id}").buildAndExpand(activityId);

        return ResponseEntity.created(uriComponents.toUri()).build();
    }


    @Operation(summary = "Update activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Update Activity",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActivityDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) }),

    })
    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable Long id, @Valid @RequestBody ActivityDto dto) {
        return ResponseEntity.accepted().body(activityServiceImp.updateActivity(id, dto));
    }

}
