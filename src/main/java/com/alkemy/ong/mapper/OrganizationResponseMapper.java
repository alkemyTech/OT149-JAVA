package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.model.Organization;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationResponseMapper {
    Organization toOrganization(OrganizationResponseMapper dto);
    OrganizationResponseDto toOrganizationResponseDto(Organization model);
}
