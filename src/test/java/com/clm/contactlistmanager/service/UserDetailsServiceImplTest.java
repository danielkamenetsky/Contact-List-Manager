package com.clm.contactlistmanager.service;

import com.clm.contactlistmanager.entity.User;
import com.clm.contactlistmanager.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_Success() {
        // Given
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        when(userRepository.findByUsername("testUser")).thenReturn(user);

        // When
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        // Then
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Given
        when(userRepository.findByUsername("testUser")).thenReturn(null);

        // Then
        assertThrows(UsernameNotFoundException.class, () -> {
            // When
            userDetailsService.loadUserByUsername("testUser");
        });
    }
}
