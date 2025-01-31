package com.stefanini.personal.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}