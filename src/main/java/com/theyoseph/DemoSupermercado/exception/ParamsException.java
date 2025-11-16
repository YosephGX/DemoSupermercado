package com.theyoseph.DemoSupermercado.exception;

public class ParamsException extends RuntimeException {

    public ParamsException() {
        super("Campos vacios o invalidos");
    }

    public ParamsException(String data) {
        super("Parametros Invalidos" + (data != null ? (": " + data) : ""));
    }
}