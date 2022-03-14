package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.MemberPagedList;
import org.springframework.data.domain.PageRequest;

public interface MemberService {

    MemberPagedList pagedList(PageRequest pageRequest);

    void updateMember(Integer id, MemberDto dto);

    void deleteMember(Integer id);

    Integer saveMember(MemberDto dto);
}
