package com.clm.contactlistmanager.repositories;

import com.clm.contactlistmanager.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

 /**
  * Repository interface for accessing and managing note entities
  * This interface extends JpaRepository, providing a range of CRUD operations
  * and some additional query methods for Note entities. Custom query methods can be
  * added to this interface to fetch or manipulate notes based on specific requirements.
  */

public interface NoteRepository extends JpaRepository<Note, Long> {
}
