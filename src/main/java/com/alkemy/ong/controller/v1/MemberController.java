package com.alkemy.ong.controller.v1;

import com.alkemy.ong.exception.ErrorDetails;
import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.service.impl.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static com.alkemy.ong.controller.ControllerConstants.V_1_MEMBERS;

@RestController
@RequestMapping(V_1_MEMBERS)
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;


    @Operation(summary = "Get a member list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieve a list of members",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberDto.class)) })
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public List<MemberDto> getAll() {
        return memberService.getAll();
    }


    @Operation(summary = "Update member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update member",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMember(@PathVariable Integer id, @Valid @RequestBody MemberDto dto) {
        memberService.updateMember(id, dto);
    }


    @Operation(summary = "Delete a member by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the member",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Member not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
    }


    @Operation(summary = "Add a new member to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create member",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PostMapping
    public ResponseEntity<Void> addMember(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MemberDto dto) {

        final int memberId = memberService.saveMember(dto);

        UriComponents uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(memberId);

        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
