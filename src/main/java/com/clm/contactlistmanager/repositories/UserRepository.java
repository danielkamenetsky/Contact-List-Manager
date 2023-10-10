package com.clm.contactlistmanager.repositories;

import com.clm.contactlistmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Defines a repository for the User entity.
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find a user by its username.
    User findByUsername(String username);

}
