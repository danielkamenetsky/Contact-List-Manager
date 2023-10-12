package com.clm.contactlistmanager.repositories;

import com.clm.contactlistmanager.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Repository interface for the Contact entity.
 * This interface abstracts the database operations for the Contact entity,
 * providing methods for CRUD operations and any other custom queries.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Get contacts in parts (or pages) instead of all at once.
    Page<Contact> findAll(Pageable pageable);
}
