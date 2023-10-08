package com.clm.contactlistmanager.repositories;

import com.clm.contactlistmanager.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
