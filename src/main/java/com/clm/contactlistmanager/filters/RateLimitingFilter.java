package com.clm.contactlistmanager.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.common.util.concurrent.RateLimiter;

/**
 * This is a custom filter that intercepts incoming HTTP requests to apply rate limiting rules.
 * It prevents clients from making too many requests within a set timeframe, thereby ensuring fair usage and preventing potential misuse.
 */

public class RateLimitingFilter implements Filter {

    // Creating a RateLimiter instance which allows 1 request per second
    private RateLimiter rateLimiter = RateLimiter.create(1.0);

    // This method initializes our filter
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    //Implements the rate limiting logic for incoming HTTP requests.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        // If our rate limiter does not allow the request (i.e., too many requests are coming in)
        if (!rateLimiter.tryAcquire()) {
            // Cast the servletResponse to HttpServletResponse to send an error
            HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
            // Send a 429 Too Many Requests response
            httpResp.setStatus(429);
            httpResp.getWriter().write("Too many requests received from your IP address, please try again later");
            return;
        }

        // If the request is allowed, continue with the next filter or the actual request if no other filters remain
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
