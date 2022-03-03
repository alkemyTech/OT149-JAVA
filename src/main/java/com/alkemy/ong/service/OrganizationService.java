package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationPutDto;
import com.alkemy.ong.dto.OrganizationValidationErrorDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OrganizationService {
    void updateOrganization(Long id, OrganizationPutDto organization);
    List<OrganizationValidationErrorDto> validationError(BindingResult result);
}
