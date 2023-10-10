package com.clm.contactlistmanager.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity  // Indicates that this is an entity class and should be mapped to a database table.
@Table(name = "roles")  // Specifies the name of the database table to be used.
public class Role {

    @Id  // Marks the field as a primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indicates that the ID should be generated automatically.
    private Long id;

    private String name;  // The name of the role (e.g., "ADMIN", "USER").

    @ManyToMany(mappedBy = "roles")  // Indicates a many-to-many relationship with the User entity.
    private Set<User> users = new HashSet<>();  // Set of users associated with this role.

}
