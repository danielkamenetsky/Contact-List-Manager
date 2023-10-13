package com.clm.contactlistmanager.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.clm.contactlistmanager.entity.Contact;
import com.clm.contactlistmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * Responsible for handling HTTP requests related to contacts.
 * It maps HTTP requests to respective service methods and provides endpoints for CRUD operations.
 * The controller uses URI versioning for the API.
 */
@RestController
@RequestMapping("/api/v1/contacts")  // Define the base URL for version 1 for this controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    // When the web asks with a GET request we give it a part of the contacts
    public Page<Contact> getAllContacts(Pageable pageable) {
        return contactService.findAll(pageable);
    }


    // Retrieve and return a contact based on its ID.
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Create a new contact
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        Contact savedContact = contactService.saveContact(contact);
        return ResponseEntity.ok(savedContact); // Return the saved contact with a 200 OK status
    }


    // Update a contact
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @Valid @RequestBody Contact contactDetails) {
        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        contact.setFirstName(contactDetails.getFirstName());
        contact.setLastName(contactDetails.getLastName());
        contact.setPhoneNumber(contactDetails.getPhoneNumber());
        contact.setEmail(contactDetails.getEmail());
        contact.setAddress(contactDetails.getAddress());
        Contact updatedContact = contactService.saveContact(contact);
        return ResponseEntity.ok(updatedContact);
    }
    // Delete a contact
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();  // Returns a 204 No Content status
    }
}