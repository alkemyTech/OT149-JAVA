package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactsRepository;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactsRepository contactsRepository;
    private final ContactMapper contactMapper;

    @Override
    public ContactDto saveContact(ContactDto dto) {
        Contact entity = this.contactMapper.toContact(dto);
        Contact entitySaved = this.contactsRepository.save(entity);
        return this.contactMapper.toContactDto(entitySaved);
    }

    @Override
    public List<ContactDto> getAll() {
        List<Contact> contacts = this.contactsRepository.findAll();
        return this.contactMapper.toContactDtoList(contacts);
    }
}
