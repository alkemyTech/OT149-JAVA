package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationPutDto;
import com.alkemy.ong.model.Organization;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationMapper {
    OrganizationPutDto toOrganizationPutDto(Organization organization);
}
