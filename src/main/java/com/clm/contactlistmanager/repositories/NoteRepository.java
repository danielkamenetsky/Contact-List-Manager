package com.clm.contactlistmanager.repositories;

import com.clm.contactlistmanager.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
