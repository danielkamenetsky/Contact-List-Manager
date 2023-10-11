package com.clm.contactlistmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Configuring the UserDetailsService bean. This is used to fetch user details.
    @Bean
    public UserDetailsService userDetailsService() {
        // Creating an in-memory user details service.
        var userDetailsService = new InMemoryUserDetailsManager();

        // Defining two users with different usernames, passwords, and authorities.
        UserDetails user1 = User.withUsername("Daniel").password("1234")
                .authorities("write").build();
        UserDetails user2 = User.withUsername("James").password("123")
                .authorities("read").build();

        // Adding the defined users to the in-memory user details service.
        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);

        return userDetailsService;
    }

    // Configuring the PasswordEncoder bean. It defines how passwords are encoded.
    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder means passwords are stored as-is without any encoding.
        // This is not secure for production use.
        return NoOpPasswordEncoder.getInstance();
    }

    // Configuring the HTTP security filter chain. This defines how HTTP requests are secured.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuring form-based authentication.
        http.formLogin(form -> form.getClass());

        // Configuring authorization rules for specific endpoints.
        http.authorizeHttpRequests
                (authorize -> authorize
                        // Only users with 'read' authority can access '/user'.
                        .requestMatchers("/user").hasAuthority("read")
                        // Only users with 'write' authority can access '/admin'.
                        .requestMatchers("/admin").hasAuthority("write"));

        return http.build();
    }
}
