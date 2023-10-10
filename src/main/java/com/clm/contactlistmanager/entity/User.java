package com.clm.contactlistmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity  // Indicates that this is an entity class and should be mapped to a database table.
@Table(name = "users")  // Specifies the name of the database table to be used.
public class User {

    @Id  // Marks the field as a primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indicates that the ID should be generated automatically.
    private Long id;

    @Getter
    private String username;  // The unique username for the user.
    @Getter
    private String password;  // The password for the user (will be encrypted).
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
