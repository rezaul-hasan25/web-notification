package com.fsmms.web_notification.core;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            logRequest(wrappedRequest);
            logResponse(wrappedResponse);
            wrappedResponse.copyBodyToResponse(); // Important!
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) throws IOException {
        String body = new String(request.getContentAsByteArray(), request.getCharacterEncoding());
        logger.info("Incoming Request: {} {} \nHeaders: {} \nBody: {}",
                request.getMethod(),
                request.getRequestURI(),
                Collections.list(request.getHeaderNames())
                        .stream()
                        .collect(Collectors.toMap(h -> h, request::getHeader)),
                body);
    }

    private void logResponse(ContentCachingResponseWrapper response) throws IOException {
        String body = new String(response.getContentAsByteArray(), response.getCharacterEncoding());
        logger.info("Response Status: {} \nBody: {}", response.getStatus(), body);
    }
}
