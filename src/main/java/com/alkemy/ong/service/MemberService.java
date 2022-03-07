package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;

import java.util.List;

public interface MemberService {

    List<MemberDto> getAll();

    void updateMember(Integer id, MemberDto dto);
  
    void deleteMember(Integer id);

    Integer saveMember(MemberDto dto);
}
