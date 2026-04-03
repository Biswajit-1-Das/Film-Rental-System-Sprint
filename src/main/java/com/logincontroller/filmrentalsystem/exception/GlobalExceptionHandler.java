package com.logincontroller.filmrentalsystem.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.NoSuchElementException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        System.out.println("Global Handler called");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), req.getRequestURI()));
    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> handleThrowable(Throwable ex, HttpServletRequest req) {
        System.out.println("Throwable handler called");
        return ResponseEntity.status(500)
                .body(new ApiError(500, ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler({
        NoSuchElementException.class, 
        EntityNotFoundException.class, 
        EmptyResultDataAccessException.class,
        NullPointerException.class,
        IndexOutOfBoundsException.class
    })
    public ResponseEntity<ApiError> handleDatabaseNotFound(Exception ex, HttpServletRequest req) {
        System.out.println("Handle Database not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND.value(),
                                 "The requested data is not present in the database.",
                                 req.getRequestURI()));
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
//        String msg = ex.getBindingResult().getFieldErrors().stream()
//                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
//                .findFirst()
//                .orElse("Validation failed");
        System.out.println("Handle Validation");
        return ResponseEntity.badRequest()
                .body(new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleBadJson(HttpMessageNotReadableException ex, HttpServletRequest req) {
        System.out.println("Handle bad json");
        return ResponseEntity.badRequest()
                .body(new ApiError(HttpStatus.BAD_REQUEST.value(),
                        "Invalid JSON or unreadable request body", req.getRequestURI()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingParam(MissingServletRequestParameterException ex,
                                                     HttpServletRequest req) {
        System.out.println("Handle missing parameter");
        return ResponseEntity.badRequest()
                .body(new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                     HttpServletRequest req) {
        System.out.println("Handle type mismatch");
        String expected = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "correct type";
        String msg = "Invalid value for parameter '" + ex.getName() + "': expected " + expected;
        return ResponseEntity.badRequest()
                .body(new ApiError(HttpStatus.BAD_REQUEST.value(), msg, req.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
        System.out.println("Handle illegal arguments");
        return ResponseEntity.badRequest()
                .body(new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), req.getRequestURI()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex, HttpServletRequest req) {
        System.out.println("Handle all exceptions ");
        String message = ex.getMessage() != null ? ex.getMessage() : ex.toString();

        // If the service threw a generic error but the message implies data is missing
        if (message.toLowerCase().contains("not found") || message.toLowerCase().contains("no value present")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiError(HttpStatus.NOT_FOUND.value(), message, req.getRequestURI()));
        }

        // If it's a real crash, return it cleanly so the frontend can display it
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, req.getRequestURI()));
    }
}