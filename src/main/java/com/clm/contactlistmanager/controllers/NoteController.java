package com.clm.contactlistmanager.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.clm.contactlistmanager.service.NoteService;
import com.clm.contactlistmanager.entity.Note;
import jakarta.validation.Valid;
/**
 * This class is responsible for handling HTTP requests related to notes associated with contacts.
 * It maps HTTP requests to respective service methods and provides endpoints for CRUD operations.
 * The controller uses URI versioning for the API.
 */
@RestController
@RequestMapping("/api/v1/notes") // This defines the base URL for version 1 for this controller
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class NoteController {

    @Autowired
    private NoteService noteService;

    // Retrieve all the notes associated with contacts.
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public Page<Note> getAllNotes(Pageable pageable) {
        return noteService.findAll(pageable);
    }


    // Get a specific note by its ID
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        // We ask the service to give us the note with this ID
        // Then, we wrap it in a ResponseEntity to send it as a response to whoever asked
        return ResponseEntity.ok().body(noteService.getNoteById(id));
    }

    // Add a new note
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        return ResponseEntity.ok().body(noteService.addNote(note));
    }

    // Update an existing note
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody Note noteDetails) {
        Note note = noteService.getNoteById(id);
        note.setNoteText(noteDetails.getNoteText());
        return ResponseEntity.ok().body(noteService.updateNote(note));
    }

    // Delete a note
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}


