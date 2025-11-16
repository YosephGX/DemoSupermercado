package com.theyoseph.DemoSupermercado.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Integer msj) {
        this(msj, null);
    }
    public NotFoundException (Integer msj, String data){
        super(
                switch (msj){
                    case 1 -> "Producto No encontrado";
                    case 2 -> "Sucursal No encontrada";
                    case 3 -> "Venta No encontrada";
                    default -> "No Encontrado";
                } + (data != null ? (": " + data) : "")
        );
    }
}