package com.clm.contactlistmanager.controllers;

import com.clm.contactlistmanager.entity.Note;
import com.clm.contactlistmanager.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.clm.contactlistmanager.config.TestSecurityConfig;
import org.springframework.context.annotation.Import;
import com.clm.contactlistmanager.service.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Integration tests for the NoteController.
 * These tests mock the service layer and validate the behavior of the controller endpoints.
 */
@Disabled("Temporarily disabled until security configuration is fixed")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NoteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setup() {
        // Mock the user details service
        UserDetails userDetails = new User("testuser", "password", new ArrayList<>());
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
    }

    // Test the retrieval of all notes.
    // It sends a GET request for all notes and expects a successful response with a list of notes.
    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllNotes() throws Exception {
        // Mocking service data
        Note note1 = new Note();
        note1.setId(1L);
        note1.setNoteText("Sample Note 1");

        Note note2 = new Note();
        note2.setId(2L);
        note2.setNoteText("Sample Note 2");

        List<Note> mockedList = Arrays.asList(note1, note2);

        when(noteService.getAllNotes()).thenReturn(mockedList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].noteText", is("Sample Note 1")))
                .andExpect(jsonPath("$[1].noteText", is("Sample Note 2")));
    }

    // Test retrieving a note by its ID.
    // It sends a GET request for a specific note ID and expects a successful response with the note data.
    @Test
    @WithMockUser(roles = "USER") 
    public void testGetNoteById() throws Exception {
        Note mockNote = new Note();
        mockNote.setId(1L);
        mockNote.setNoteText("Sample Note 1");

        when(noteService.getNoteById(1L)).thenReturn(mockNote);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.noteText", is("Sample Note 1")));
    }

    // Test updating an existing note.
    //It sends a PUT request with updated note data for a specific ID and expects a successful response with the updated data.
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateNote() throws Exception {
        Note existingNote = new Note();
        existingNote.setId(1L);
        existingNote.setNoteText("Sample Note 1");

        Note updatedNote = new Note();
        updatedNote.setId(1L);
        updatedNote.setNoteText("Updated Sample Note");

        when(noteService.getNoteById(1L)).thenReturn(existingNote);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/notes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"noteText\": \"Updated Sample Note\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.noteText", is("Updated Sample Note")));
    }


    // Test deleting a note by its ID.
    // It sends a DELETE request for a specific note ID and expects a successful response indicating the note has been deleted.
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteNote() throws Exception {
        Note mockNote = new Note();
        mockNote.setId(1L);
        mockNote.setNoteText("Sample Note 1");

        when(noteService.getNoteById(1L)).thenReturn(mockNote);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/notes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
