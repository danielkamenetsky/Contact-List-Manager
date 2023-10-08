package com.clm.contactlistmanager.entity;

import jakarta.persistence.*;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "note_text")
    private String noteText;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public Note() {
        // Default constructor
    }

    // Getters, setters, and other methods go here
}
