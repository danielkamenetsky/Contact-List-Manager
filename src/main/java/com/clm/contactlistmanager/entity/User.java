package com.clm.contactlistmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a User entity which holds the authentication and authorization details
 * for each user of the application. Each user has a unique username and a password,
 * and can be assigned multiple roles determining their access rights.
 * The relationship between a user and roles is modeled as a many-to-many.
 */

@Entity
@Table(name = "users")
public class User {

    @Id  // Marks the field as a primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indicates that the ID should be generated automatically.
    private Long id;

    @Getter
    @Setter
    private String username;  // The unique username for the user.
    @Getter
    @Setter
    private String password;  // The password for the user (will be encrypted).

    @Getter
    @Setter
    private boolean enabled;  // Indicates if the user account is active or not.

    @ManyToMany(fetch = FetchType.EAGER)  // Indicates a many-to-many relationship with the Role entity.
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();  // Set of roles associated with the user.

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
