package com.clm.contactlistmanager.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clm.contactlistmanager.filters.RateLimitingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// This annotation tells Spring that this class is a configuration class
@Configuration
public class RateLimitingConfiguration {

    // This method returns a bean of our RateLimitingFilter. Spring will manage this bean.
    @Bean
    public RateLimitingFilter rateLimiter() {
        return new RateLimitingFilter();
    }
}
