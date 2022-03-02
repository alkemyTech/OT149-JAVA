package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationPutDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    OrganizationsRepository repository;
    OrganizationMapper mapper;
    public void updateOrganization(Long id, OrganizationPutDto dto){
        Organization organization = repository.getById(id);
        organization.setName(dto.getName());
        organization.setImages(dto.getImages());
        organization.setAddress(dto.getAddress());
        organization.setPhone(dto.getPhone());
        organization.setEmail(dto.getEmail());
        organization.setWelcomeText(dto.getWelcomeText());
        organization.setAboutUsText(dto.getAboutUsText());
        repository.save(organization);
    }
}
