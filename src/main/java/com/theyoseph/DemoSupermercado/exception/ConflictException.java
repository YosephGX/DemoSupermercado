package com.theyoseph.DemoSupermercado.exception;

public class ConflictException extends RuntimeException {
    public ConflictException() {
        super("Recurso en conflicto");
    }

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}