package com.clm.contactlistmanager.dto;

// This is a Data Transfer Object for our Contact.
// This class is used to share contact data in a specific format.
public class ContactDTO {

    // Attributes for the Contact data transfer object
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;

    // Getter for ID
    public Long getId() {
        return id;
    }

    // Getter for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter for last name
    public String getLastName() {
        return lastName;
    }

    // Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Setter for ID
    public void setId(Long id) {
        this.id = id;
    }

    // Setter for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Setter for last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }
}
