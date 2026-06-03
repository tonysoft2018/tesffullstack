package com.github.wilberargueta.backend.backend_test_argueta.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
    

}
