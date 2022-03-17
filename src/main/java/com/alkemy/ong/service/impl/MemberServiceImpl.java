package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.MemberPagedList;
import com.alkemy.ong.exception.MemberNotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.MembersRepository;
import com.alkemy.ong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MembersRepository membersRepository;
    private final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    @Override
    public MemberPagedList pagedList(PageRequest pageRequest) {

        Page<Member> pageMember = membersRepository.findAll(pageRequest);

        final List<MemberDto> list = pageMember.getContent().stream().map(memberMapper::toDto).collect(Collectors.toList());
        final PageRequest of = PageRequest.of(pageMember.getPageable().getPageNumber(), pageMember.getPageable().getPageSize());
        final long totalElements = pageMember.getTotalElements();

        return new MemberPagedList(list, of, totalElements);
    }

    @Transactional
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

    @Transactional
    @Override
    public void deleteMember(Integer id) {
        membersRepository.findById(id).ifPresent(membersRepository::delete);
    }

    @Transactional
    @Override
    public Integer saveMember(MemberDto dto) {

        Member member = memberMapper.toMember(dto);

        membersRepository.save(member);

        return member.getId();
    }

}
