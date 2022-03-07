package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto saveContact(ContactDto dto);

    List<ContactDto> getAll();

}
