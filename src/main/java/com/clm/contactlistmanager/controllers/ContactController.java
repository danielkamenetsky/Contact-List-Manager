package com.clm.contactlistmanager.controllers;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.clm.contactlistmanager.entity.Contact;
import com.clm.contactlistmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")  // This defines the base URL for this controller
public class ContactController {

    @Autowired  // This injects the ContactService into our controller
    private ContactService contactService;

    @GetMapping
    // When the web asks with a GET request we give it a part of the contacts
    public Page<Contact> getAllContacts(Pageable pageable) {
        return contactService.findAll(pageable);
    }
}