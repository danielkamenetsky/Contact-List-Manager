package com.clm.contactlistmanager.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Represents a Role entity which defines specific roles for user authorization.
 * Each user can have multiple roles, and each role can grant different levels of access
 * to specific functionalities in the application. Examples of roles include "ADMIN", "USER", etc.
 * The relationship between a user and its roles is modeled as a many-to-many.
 */

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
