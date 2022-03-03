package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationPutDto;
import com.alkemy.ong.dto.OrganizationValidationErrorDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationsRepository repository;
    @Autowired
    private OrganizationMapper mapper;
    @Override
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
    @Override
    public List<OrganizationValidationErrorDto>validationError(BindingResult result){

        return result.getFieldErrors().stream().map(err -> {
            return OrganizationValidationErrorDto.builder()
                    .field(err.getField())
                    .value((String) result.getFieldValue(err.getField()))
                    .message(err.getDefaultMessage())
                    .build();
        }).collect(Collectors.toList());
    }
}
