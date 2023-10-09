package com.clm.contactlistmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clm.contactlistmanager.service.NoteService;
import com.clm.contactlistmanager.entity.Note;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notes") // Sets the base URL for all endpoints in this controller to be /api/v1/notes
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


    // Get all notes
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok().body(noteService.getAllNotes());
    }

    // Get a specific note by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        // We ask the service to give us the note with this ID
        // Then, we wrap it in a ResponseEntity to send it as a response to whoever asked
        return ResponseEntity.ok().body(noteService.getNoteById(id));
    }

    // Add a new note
    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        return ResponseEntity.ok().body(noteService.addNote(note));
    }

    // Update an existing note
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody Note noteDetails) {
        Note note = noteService.getNoteById(id);
        note.setNoteText(noteDetails.getNoteText()); 
        return ResponseEntity.ok().body(noteService.updateNote(note));
    }

    // Delete a note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}


