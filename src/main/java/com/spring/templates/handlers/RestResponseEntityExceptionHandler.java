package com.spring.templates.handlers;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RepositoryConstraintViolationException.class})
    public ResponseEntity<Object> handleRepositoryConstraintViolationException(Exception ex, WebRequest request) {
        RepositoryConstraintViolationException nevEx = (RepositoryConstraintViolationException) ex;

        String errors = nevEx.getErrors().getAllErrors().stream()
                .map(ObjectError::toString)
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<Object>(errors, new HttpHeaders(),
                HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
        ConstraintViolationException nevEx = (ConstraintViolationException) ex;

        String errors = nevEx.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<Object>(errors, new HttpHeaders(),
                HttpStatus.PARTIAL_CONTENT);
    }
}
