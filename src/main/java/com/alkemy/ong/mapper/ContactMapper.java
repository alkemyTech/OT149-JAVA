package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import org.mapstruct.Mapper;

import java.util.List;


public interface ContactMapper {
    List<ContactDto> toContactDtoList(List<Contact> contacts);
}
