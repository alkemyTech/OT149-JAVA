package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;

import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactsRepository;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactsRepository contactsRepository;
    private final ContactMapper contactMapper;

    /**
     * This method saves a new contact into database.
     *
     * @param dto The new contact to be saved as ContactDto
     * @return The contact saved as ContactDto
     */
    @Transactional
    @Override
    public ContactDto saveContact(ContactDto dto) {
        Contact entity = this.contactMapper.toContact(dto);
        Contact entitySaved = this.contactsRepository.save(entity);
        return this.contactMapper.toContactDto(entitySaved);
    }

    /**
     * This method gets all the active contacts saved into database
     *
     * @return The list of contacts as List ContactDto
     */
    @Transactional(readOnly = true)
    @Override
    public List<ContactDto> getAll() {
        List<Contact> contacts = this.contactsRepository.findAll();
        return this.contactMapper.toContactDtoList(contacts);
    }
}
