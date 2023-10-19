package com.hnasc.orderproducts.controllers.exceptions;

import com.hnasc.orderproducts.services.exceptions.InvalidRoleException;
import com.hnasc.orderproducts.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException err, HttpServletRequest request) {
        String error = "Resource not found.";
        var status = HttpStatus.NOT_FOUND;
        var body = new StandardError(Instant.now(), status.value(), error, err.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> DataIntegrityViolation(DataIntegrityViolationException err, HttpServletRequest request) {
        String error = "Data Integrity Violation.";
        var status = HttpStatus.BAD_REQUEST;
        var body = new StandardError(Instant.now(), status.value(), error, err.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<StandardError> InvalidRole(InvalidRoleException err, HttpServletRequest request) {
        String error = "Invalid Role.";
        var status = HttpStatus.BAD_REQUEST;
        var body = new StandardError(Instant.now(), status.value(), error, err.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }
}
