package com.clm.contactlistmanager.service;

import com.clm.contactlistmanager.entity.Contact;
import com.clm.contactlistmanager.exceptions.ResourceNotFoundException;
import com.clm.contactlistmanager.repositories.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Test
    public void testFindAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        when(contactRepository.findAll()).thenReturn(contacts);

        List<Contact> result = contactService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetContactById_validId() {
        Contact contact = new Contact();
        contact.setId(1L);
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Contact result = contactService.getContactById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetContactById_invalidId() {
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> contactService.getContactById(1L));
    }

    @Test
    public void testSaveContact_validContact() {
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact result = contactService.saveContact(contact);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void testSaveContact_invalidContact() {
        Contact contact = new Contact();
        assertThrows(RuntimeException.class, () -> contactService.saveContact(contact));
    }

    @Test
    public void testDeleteContact() {
        Long id = 1L;
        doNothing().when(contactRepository).deleteById(id);
        contactService.deleteContact(id);
        verify(contactRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindAllPageableContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        when(contactRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        Page<Contact> result = contactService.findAll(Pageable.unpaged());

        assertNotNull(result);
    }
}

