package com.theyoseph.DemoSupermercado.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Acceso denegado");
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}