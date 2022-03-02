package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationPutDto;

public interface OrganizationService {
    void updateOrganization(Long id, OrganizationPutDto organization);
}
