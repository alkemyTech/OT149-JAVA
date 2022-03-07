package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public List<MemberDto> getAll() {
        return memberService.getAll();
    }

    @PostMapping
    public ResponseEntity<Void> addMember(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MemberDto dto) {

        final int memberId = memberService.saveMember(dto);

        UriComponents uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(memberId);

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

}
