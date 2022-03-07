package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.MemberNotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MembersRepository;
import com.alkemy.ong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MembersRepository membersRepository;
    private final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    @Override
    public List<MemberDto> getAll() {
        return membersRepository.findAll().stream().map(memberMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void updateMember(Integer id, MemberDto dto) {

        membersRepository.findById(id).map(member -> {
            member.setName(dto.getName());
            member.setFacebookUrl(dto.getFacebookUrl());
            member.setInstagramUrl(dto.getInstagramUrl());
            member.setLinkedinUrl(dto.getLinkedinUrl());
            member.setImage(dto.getImage());
            member.setDescription(dto.getDescription());

            return membersRepository.save(member);

        }).orElseThrow(() -> {
            throw new MemberNotFoundException();
        });

    }

}
