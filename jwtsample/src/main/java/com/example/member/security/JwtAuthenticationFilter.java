package com.example.member.security;

import com.example.member.dto.ErrorResponse;
import com.example.member.exception.AuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            log.debug("Processing request to {}", request.getRequestURI());

            if (jwt != null) {
                if (tokenProvider.validateToken(jwt)) {
                    Authentication auth = tokenProvider.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    throw new AuthenticationException("Invalid JWT");
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            log.error("Authentication failed: {}", ex.getMessage());
            SecurityContextHolder.clearContext();
            handleAuthenticationError(response, ex.getMessage(),
                request.getRequestURL().toString());
        }

    }

    private void handleAuthenticationError(HttpServletResponse response, String message,
        String path) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(),
            HttpServletResponse.SC_UNAUTHORIZED,
            "Authentication Failed",
            message,
            path
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
