package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.model.Organization;

import java.util.List;

public interface OrganizationService {
    OrganizationResponseDto getOrganization(Long id);
    Organization findById(Long id);
}

