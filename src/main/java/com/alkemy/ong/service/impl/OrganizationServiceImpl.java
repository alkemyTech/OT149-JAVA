package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationResponseMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationsRepository organizationsRepository;
    private final OrganizationResponseMapper organizationResponseMapper;


    @Override
    public OrganizationResponseDto getOrganization(Long id) {
        return organizationsRepository.findById(id)
                .map(organizationResponseMapper::toOrganizationResponseDto)
                .orElseThrow(() -> new NotFoundException("Organization id not found - " + id));
    }
    
    @Override
    public Organization findById(Long id) {
    	
    	return organizationsRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Organization id not found - " + id));
    }

}
