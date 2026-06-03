package com.github.wilberargueta.backend.backend_test_argueta.config;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.wilberargueta.backend.backend_test_argueta.dto.response.GenericResponse;
import com.github.wilberargueta.backend.backend_test_argueta.exceptions.NotFoundException;

@RestControllerAdvice
public class ExceptionController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NotFoundException.class)
     public ResponseEntity<GenericResponse<Void>> general(NotFoundException ex) {

        return ResponseEntity.ok(GenericResponse.<Void>builder().message(ex.getMessage()).status(false).build());
    }    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<GenericResponse<Void>> general(MethodArgumentNotValidException ex) {

        String collectDefaultMessage = ex.getAllErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.joining("\n-"));

        return ResponseEntity.ok(GenericResponse.<Void>builder().message(collectDefaultMessage).status(false).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<Void>> general(Exception ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity.ok(GenericResponse.<Void>builder().message(ex.getMessage()).status(false).build());
    }

}
