package com.clm.contactlistmanager.controllers;

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

    @GetMapping  // This indicates the HTTP method (GET in this case)
    public Iterable<Contact> getAllContacts() {
        return contactService.findAll();
    }
}
