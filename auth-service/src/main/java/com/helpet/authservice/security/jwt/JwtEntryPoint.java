package com.helpet.authservice.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpet.authservice.security.dto.MessageDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        logger.error("token not found or invalid");
        // res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
        MessageDto dto = new MessageDto(HttpStatus.UNAUTHORIZED, "token not found or invalid");
        res.setContentType("application/json");
        res.setStatus(dto.getStatus().value());
        res.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        res.getWriter().flush();
        res.getWriter().close();
    }
    
}
