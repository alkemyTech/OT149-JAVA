package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationResponseDto;
import java.util.List;

public interface OrganizationService {
    OrganizationResponseDto getOrganization(Long id);
    void updateOrganization(Long id, OrganizationPutDto organization);
    List<OrganizationValidationErrorDto> validationError(BindingResult result);
}

