package com.onefineday.ratelimiter.exceptions;

import com.onefineday.ratelimiter.utilities.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HashMap<Object, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ApiResponse<Object> response = new ApiResponse<>(Collections.emptyList(), false, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        System.out.println(ex.getLocalizedMessage());
        List<String> errors = List.of(ex.getMessage().split(":")[0]);
        ApiResponse<Object> response = new ApiResponse<>(Collections.emptyList(), false, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Additional exception handlers can be added as needed
}