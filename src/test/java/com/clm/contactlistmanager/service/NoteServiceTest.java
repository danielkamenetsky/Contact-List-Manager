package com.clm.contactlistmanager.service;

import com.clm.contactlistmanager.entity.Note;
import com.clm.contactlistmanager.exceptions.ResourceNotFoundException;
import com.clm.contactlistmanager.repositories.NoteRepository;
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
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void testFindAllPageableNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note());
        when(noteRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        Page<Note> result = noteService.findAll(Pageable.unpaged());

        assertNotNull(result);
    }

    @Test
    public void testGetAllNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note());
        when(noteRepository.findAll()).thenReturn(notes);

        List<Note> result = noteService.getAllNotes();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetNoteById_validId() {
        Note note = new Note();
        note.setId(1L);
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Note result = noteService.getNoteById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetNoteById_invalidId() {
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> noteService.getNoteById(1L));
    }

    @Test
    public void testAddNote_validNote() {
        Note note = new Note();
        note.setNoteText("Sample note");
        when(noteRepository.save(note)).thenReturn(note);

        Note result = noteService.addNote(note);

        assertNotNull(result);
        assertEquals("Sample note", result.getNoteText());
    }

    @Test
    public void testAddNote_invalidNote() {
        Note note = new Note();
        assertThrows(RuntimeException.class, () -> noteService.addNote(note));
    }

    @Test
    public void testUpdateNote() {
        Note note = new Note();
        note.setId(1L);
        note.setNoteText("Sample note");
        when(noteRepository.save(note)).thenReturn(note);

        Note result = noteService.updateNote(note);

        assertNotNull(result);
        assertEquals("Sample note", result.getNoteText());
    }

    @Test
    public void testDeleteNote() {
        Long id = 1L;
        doNothing().when(noteRepository).deleteById(id);
        noteService.deleteNote(id);
        verify(noteRepository, times(1)).deleteById(id);
    }
}
