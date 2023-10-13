package com.clm.contactlistmanager.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.clm.contactlistmanager.filters.RateLimitingFilter;

/**
 * Rate Limiting Configuration - This class sets up the configuration for rate limiting, ensuring that
 * the API is not misused by excessive requests within a short period of time.
 */

@Configuration
public class RateLimitingConfiguration {

    // This method returns a bean of our RateLimitingFilter. Spring will manage this bean.
    @Bean
    public RateLimitingFilter rateLimiter() {
        return new RateLimitingFilter();
    }
}
