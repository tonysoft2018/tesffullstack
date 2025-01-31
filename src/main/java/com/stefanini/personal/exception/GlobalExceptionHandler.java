package com.stefanini.personal.exception;

import com.stefanini.personal.domain.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handlePersonNotFoundException(PersonNotFoundException ex) {
        System.out.println("Capturando PersonNotFoundException: " + ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoDataFoundException(NoDataFoundException ex) {
        System.out.println("Capturando NoDataFoundException: " + ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreatePersonException.class)
    public ResponseEntity<ApiResponse<?>> handleCreatePersonException(CreatePersonException ex) {
        System.out.println("Capturando CreatePersonException: " + ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception ex) {
        System.out.println("Capturando excepción genérica: " + ex.getMessage());
        ex.printStackTrace(); // Imprime el stack trace para más detalles
        ApiResponse<?> response = new ApiResponse<>(false, "Ocurrió un error en el servidor", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}