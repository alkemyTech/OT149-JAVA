package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationResponseMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImp implements OrganizationService {

    private final OrganizationsRepository organizationsRepository;
    private final OrganizationResponseMapper organizationResponseMapper;

    @Override
    public OrganizationResponseDto getOrganization(Long id) {

        Organization org = null;
        Optional<Organization> result = organizationsRepository.findById(id);


        if(result.isPresent()) {
            org = result.get();
            OrganizationResponseDto newDto = organizationResponseMapper.toOrganizationResponseDto(org);
            return newDto;
        }else{
            throw new NotFoundException("Organization id not found - " + id);
        }


    }
}
