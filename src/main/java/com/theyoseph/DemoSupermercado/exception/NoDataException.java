package com.theyoseph.DemoSupermercado.exception;

public class NoDataException extends RuntimeException {

    public NoDataException(Integer msj) {
        super(
                switch (msj) {
                    case 1 -> "No hay productos registrados";
                    case 2 -> "No hay sucursales registradas";
                    case 3 -> "No hay ventas registradas";
                    default -> "No hay datos registrados";
                }
        );
    }

    public NoDataException() {
        super("No hay datos disponibles");
    }
}
