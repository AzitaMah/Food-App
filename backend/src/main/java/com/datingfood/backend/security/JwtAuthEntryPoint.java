package com.datingfood.backend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Handles Exceptions before Servlet if an unauthorized user wants access to an endpoint
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Setzt den HTTP-Antwortstatus auf "UNAUTHORIZED" (401) und sendet die Fehlermeldung der AuthenticationException als Response.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

    }
}
