package com.clm.contactlistmanager.controllers;

import com.clm.contactlistmanager.entity.Contact;
import com.clm.contactlistmanager.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the ContactController.
 * These tests mock the service layer and validate the behavior of the controller endpoints.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactController.class)
public class ContactControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    //Test the creation of a new contact.
    //It sends a POST request with contact data and expects a successful response with the same data.
    @Test
    public void testGetAllContacts() throws Exception {
        // Mocking service data
        Contact contact1 = new Contact();
        contact1.setId(1L);
        contact1.setFirstName("John");
        contact1.setLastName("Doe");

        Contact contact2 = new Contact();
        contact2.setId(2L);
        contact2.setFirstName("Jane");
        contact2.setLastName("Smith");

        List<Contact> mockedList = Arrays.asList(contact1, contact2);

        when(contactService.findAll()).thenReturn(mockedList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Doe")))
                .andExpect(jsonPath("$[1].firstName", is("Jane")))
                .andExpect(jsonPath("$[1].lastName", is("Smith")));
    }


    // Test updating an existing note.
    //It sends a PUT request with updated note data for a specific ID and expects a successful response with the updated data.
    @Test
    public void testUpdateContact() throws Exception {
        Contact existingContact = new Contact();
        existingContact.setId(1L);
        existingContact.setFirstName("John");
        existingContact.setLastName("Doe");

        Contact updatedContact = new Contact();
        updatedContact.setId(1L);
        updatedContact.setFirstName("Jane");
        updatedContact.setLastName("Doe");

        when(contactService.getContactById(1L)).thenReturn(existingContact);
        when(contactService.saveContact(updatedContact)).thenReturn(updatedContact);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Jane\", \"lastName\": \"Doe\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Jane")))
                .andExpect(jsonPath("$.lastName", is("Doe")));
    }

    // Test deleting a contact by its ID.
    // It sends a DELETE request for a specific contact ID and expects a successful response indicating the contact has been deleted.
    @Test
    public void testDeleteContact() throws Exception {
        Contact mockContact = new Contact();
        mockContact.setId(1L);
        mockContact.setFirstName("John");
        mockContact.setLastName("Doe");

        when(contactService.getContactById(1L)).thenReturn(mockContact);
        doNothing().when(contactService).deleteContact(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    // **Missing integration test to be added in the future**
    // Test retrieving a contact by its ID.
    // It sends a GET request for a specific contact ID and expects a successful response with the contact data.
    @Test
    public void testGetContactById() throws Exception {
        // ... [test code]
    }
}
