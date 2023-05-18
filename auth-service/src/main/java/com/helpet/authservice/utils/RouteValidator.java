package com.helpet.authservice.utils;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.helpet.authservice.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties(prefix = "admin-paths")
@Data
@AllArgsConstructor @NoArgsConstructor
public class RouteValidator {
    private List<RequestDto> paths;

    public boolean isAdminPath(RequestDto requestDto){
        return paths.stream().anyMatch(p ->
        Pattern.matches(p.getUri(), requestDto.getUri()) && p.getMethod().equals(requestDto.getMethod()));
    }
}
