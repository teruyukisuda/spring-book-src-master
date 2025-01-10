package com.example.member.exception;

import com.example.member.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e,
//        HttpServletRequest request) {
//        ErrorResponse authenticationFailed = new ErrorResponse(
//            LocalDateTime.now(),
//            HttpStatus.UNAUTHORIZED.value(),
//            "Authentication Failed",
//            e.getMessage(),
//            request.getRequestURI()
//        );
//        return new ResponseEntity<>(authenticationFailed, HttpStatus.UNAUTHORIZED);
//    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> hadleAccessDeniedException(AuthenticationException e,
        HttpServletRequest request) {
        ErrorResponse authenticationFailed = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.FORBIDDEN.value(),
            "Access Denied",
            "You dont have permission to access this resource",
            request.getRequestURI()
        );
        return new ResponseEntity<>(authenticationFailed, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> hadleAllException(AuthenticationException e,
        HttpServletRequest request) {
        ErrorResponse authenticationFailed = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            e.getMessage(),
            request.getRequestURI()
        );
        return new ResponseEntity<>(authenticationFailed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
