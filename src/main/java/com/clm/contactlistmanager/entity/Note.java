package com.clm.contactlistmanager.entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.*;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


/**
 * Represents a Note entity associated with a Contact.
 * Each note is related to a specific contact and contains details
 * like the note text and the date it was created. The relationship
 * between a contact and its notes is modeled as a many-to-one,
 * where a contact can have multiple notes.
 */

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The ID of the associated contact
    @Column(name = "contact_id")
    private Long contactId;

    // The text content of the note
    @NotEmpty(message = "Note text cannot be empty")
    @Column(name = "note_text")
    private String noteText;

    // The timestamp indicating when the note was created
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public Note() {
        // Default constructor
    }

    // Below are standard getter and setter methods
    public Long getId() {
        return id;
    }

    public Long getContactId() {
        return contactId;
    }

    public String getNoteText() {
        return noteText;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
