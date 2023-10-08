package com.clm.contactlistmanager.service;

import com.clm.contactlistmanager.entity.Note;
import com.clm.contactlistmanager.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Fetch all notes
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Fetch a specific note by ID
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    // Add a new note
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    // Update an existing note
    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }

    // Delete a note by ID
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
