package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ContactMapper {

    Contact toContact(ContactDto dto);

    ContactDto toContactDto(Contact contact);
    List<ContactDto> toContactDtoList(List<Contact> contacts);


}
