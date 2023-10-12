package com.clm.contactlistmanager.dto;
import java.util.Date;
/**
 * This DTO provides a representation of a Note that can be used in
 * API responses or requests, abstracting away certain details of the underlying entity.
 */
public class NoteDTO {

    // Attributes for the Note data transfer object
    private Long id;
    private Long contactId;
    private String noteText;
    private Date dateCreated;

    // Getter for ID
    public Long getId() {
        return id;
    }

    // Getter for the associated contact's ID
    public Long getContactId() {
        return contactId;
    }

    // Getter for the note text
    public String getNoteText() {
        return noteText;
    }

    // Getter for the date the note was created
    public Date getDateCreated() {
        return dateCreated;
    }

    // Setter for ID
    public void setId(Long id) {
        this.id = id;
    }

    // Setter for the associated contact's ID
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    // Setter for the note text
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    // Setter for the date the note was created
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
