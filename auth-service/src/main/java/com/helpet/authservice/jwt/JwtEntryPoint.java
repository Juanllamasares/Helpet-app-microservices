package com.helpet.authservice.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpet.authservice.dto.MessageDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        logger.error("Token not found or invalid");
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
        MessageDto dto = new MessageDto(HttpStatus.UNAUTHORIZED,"Token not found or invalid");
        response.setContentType("application/json");
        response.setStatus(dto.getStatus().value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }

    
}
