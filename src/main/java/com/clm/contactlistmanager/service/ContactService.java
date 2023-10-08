package com.clm.contactlistmanager.service;

import com.clm.contactlistmanager.entity.Contact;
import com.clm.contactlistmanager.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // This tells Spring that our class is a special 'service' class.
public class ContactService {

    @Autowired  // Spring will automatically give us a ready-to-use version of ContactRepository.
    private ContactRepository contactRepository;

    // This method fetches all contacts from the database
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    // Get one contact using its ID.
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);  // If the contact isn't found, we'll get back null.
    }

    // Save a new contact or update an existing one.
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

}

