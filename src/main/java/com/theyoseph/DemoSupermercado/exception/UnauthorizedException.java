package com.theyoseph.DemoSupermercado.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Acceso no autorizado");
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
