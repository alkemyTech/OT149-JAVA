package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Member;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper
public interface MemberMapper {

    Member toMember(MemberDto dto);

    MemberDto toDto(Member member);
}
