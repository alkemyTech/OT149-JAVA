package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {

    Contact toContact(ContactDto dto);

    ContactDto toContactDto(Contact contact);

}
