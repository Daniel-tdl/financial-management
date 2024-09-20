package br.com.jmt.financial_management.domain.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorResponseWrapper> handleException(Exception exception, WebRequest request) {

        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();

        return ResponseEntity
                .badRequest()
                .body(new HttpErrorResponseWrapper(BAD_REQUEST, exception.getMessage(), requestURI));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<HttpErrorResponseWrapper> handleException(ConstraintViolationException exception, WebRequest request) {
        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();

        Set<String> constraintViolations = exception.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

        return ResponseEntity
                .badRequest()
                .body(new HttpErrorResponseWrapper(BAD_REQUEST, exception.getMessage(), requestURI, constraintViolations));
    }

    @ExceptionHandler(ErrorAccountException.class)
    public ResponseEntity<HttpErrorResponseWrapper> handleErrorAccountException(ErrorAccountException ex, WebRequest request) {

        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        String message = ex.getMessage();
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(new HttpErrorResponseWrapper(status, message, requestURI));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<HttpErrorResponseWrapper> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest request) {

        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        String message = ex.getMessage();
        final HttpStatus status = BAD_REQUEST;
        return ResponseEntity
                .status(status)
                .body(new HttpErrorResponseWrapper(status, message, requestURI));
    }

    @ExceptionHandler(DataInvalidException.class)
    public ResponseEntity<HttpErrorResponseWrapper> handleAccountNotFoundException(DataInvalidException ex, WebRequest request) {

        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        String message = ex.getMessage();
        final HttpStatus status = BAD_REQUEST;
        return ResponseEntity
                .status(status)
                .body(new HttpErrorResponseWrapper(status, message, requestURI));
    }

    @ExceptionHandler(UploadFileException.class)
    public ResponseEntity<HttpErrorResponseWrapper> handleUploadFileException(UploadFileException ex, WebRequest request) {

        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        String message = ex.getMessage();
        final HttpStatus status = INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(new HttpErrorResponseWrapper(status, message, requestURI));
    }
}