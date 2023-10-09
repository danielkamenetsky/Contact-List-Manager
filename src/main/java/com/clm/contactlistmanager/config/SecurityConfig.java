package com.clm.contactlistmanager.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
// Enables Spring's built-in web security configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean annotation indicates that this method will provide a bean (component) to the Spring container.
    // This bean is of type UserDetailsService which is used by Spring Security to fetch user details.
    @Bean
    public UserDetailsService userDetailsService() {
        // Creating a default user for demonstration purposes
        // The user has the username "user", password "password", and a role of "USER"
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        // InMemoryUserDetailsManager is a simple user details service provided by Spring
        // that stores user details in memory.
        return new InMemoryUserDetailsManager(user);
    }
}