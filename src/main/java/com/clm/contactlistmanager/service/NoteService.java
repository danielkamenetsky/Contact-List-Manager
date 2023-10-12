package com.clm.contactlistmanager.service;
import jakarta.validation.Valid;
import com.clm.contactlistmanager.entity.Note;
import com.clm.contactlistmanager.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.clm.contactlistmanager.dto.NoteDTO;
import com.clm.contactlistmanager.exceptions.ResourceNotFoundException;
import com.clm.contactlistmanager.exceptions.InvalidInputException;
import java.util.List;

/**
 * Service class responsible for handling business logic associated with Notes.
 * This includes operations like fetching all notes, retrieving a specific note by ID,
 * saving a new note, updating an existing note, and deleting a note.
 * The service uses the NoteRepository for data access and performs necessary
 * transformations, validations, and exception handling. Additionally, it's responsible
 * for converting between Note entities and NoteDTOs.
 */

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // This function gets all notes, but not all at once, only a page at a time.
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    // Fetch all notes without pagination
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Fetch a specific note by ID
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Note not found with id: " + id)
        );
    }
    // Add a new note
    public Note addNote(@Valid Note note) {
        if (note.getNoteText() == null) {
            throw new InvalidInputException("Note text cannot be null.");
        }
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
    // Convert an entity to a DTO
    private NoteDTO convertToDTO(Note note) {
        NoteDTO dto = new NoteDTO();
        dto.setId(note.getId());
        dto.setContactId(note.getContactId());
        dto.setNoteText(note.getNoteText());
        dto.setDateCreated(note.getDateCreated());
        return dto;
    }

    // Convert a DTO to an entity
    private Note convertFromDTO(NoteDTO dto) {
        Note note = new Note();
        note.setId(dto.getId());
        note.setContactId(dto.getContactId());
        note.setNoteText(dto.getNoteText());
        note.setDateCreated(dto.getDateCreated());
        return note;
    }
}
