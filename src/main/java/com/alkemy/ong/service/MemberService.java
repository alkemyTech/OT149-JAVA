package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;

import java.util.List;

public interface MemberService {

    List<MemberDto> getAll();

    Integer saveMember(MemberDto dto);
}
