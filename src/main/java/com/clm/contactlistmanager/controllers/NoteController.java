package com.clm.contactlistmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clm.contactlistmanager.service.NoteService;
import com.clm.contactlistmanager.entity.Note;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // This method retrieves all the notes associated with contacts.
    // Notes provide additional information about a contact, like:
    // - Specific memories related to the contact.
    // - Reminders about the contact's preferences.
    // - Any other relevant information to remember about the contact.
    @GetMapping
    public Page<Note> getAllNotes(Pageable pageable) {
        return noteService.findAll(pageable);
    }
}
