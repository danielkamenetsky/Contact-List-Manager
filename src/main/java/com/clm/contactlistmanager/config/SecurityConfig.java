package com.clm.contactlistmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    // This bean provides in-memory user details service.
    @Bean
    public UserDetailsService userDetailsService() {
        // Define users in memory with their username, password, and authorities.
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("Daniel").password(passwordEncoder().encode("1234")).authorities("write").build());
        manager.createUser(User.withUsername("James").password(passwordEncoder().encode("123")).authorities("read").build());
        return manager;
    }

    // This bean provides authentication provider that uses the custom UserDetailsService and BCrypt password encoder.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Password encoder bean. BCrypt is used here as it's a strong hashing function.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security configuration for HTTP requests.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Disabling CSRF (Cross-Site Request Forgery) so application does not
       // require CSRF tokens to be sent with state-changing requests (like POST, PUT, DELETE) for testing purposes.
                http.csrf(csrf -> csrf.disable())
                // Define authorization rules.
                .authorizeRequests()
                .requestMatchers(antMatcher(HttpMethod.DELETE, "/api/v1/contacts/**")).hasRole("ADMIN")
                .requestMatchers(antMatcher("/api/v1/contacts/**")).hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
