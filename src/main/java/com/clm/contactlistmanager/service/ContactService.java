package com.clm.contactlistmanager.service;
import jakarta.validation.Valid;
import com.clm.contactlistmanager.entity.Contact;
import com.clm.contactlistmanager.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.clm.contactlistmanager.dto.ContactDTO;
import com.clm.contactlistmanager.exceptions.ResourceNotFoundException;
import com.clm.contactlistmanager.exceptions.InvalidInputException;



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
        return contactRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Contact not found with id: " + id)
        );
    }

    // Save a new contact or update an existing one.
    public Contact saveContact(@Valid Contact contact) {
        if (contact.getFirstName() == null || contact.getLastName() == null) {
            throw new InvalidInputException("Contact's first name and last name cannot be null.");
        }
        return contactRepository.save(contact);
    }

    // Get a part of the contacts.
    public Page<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }
    // This method converts a Contact entity into a ContactDTO format.
    // Convert a Contact entity to ContactDTO
    private ContactDTO convertToDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setPhoneNumber(contact.getPhoneNumber());
        dto.setEmail(contact.getEmail());
        dto.setAddress(contact.getAddress());
        return dto;
    }

    // Convert a ContactDTO to Contact entity
    private Contact convertToEntity(ContactDTO dto) {
        Contact contact = new Contact();
        contact.setId(dto.getId());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setEmail(dto.getEmail());
        contact.setAddress(dto.getAddress());
        return contact;
    }
}

