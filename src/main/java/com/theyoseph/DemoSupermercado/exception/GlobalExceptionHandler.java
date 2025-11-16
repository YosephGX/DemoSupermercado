package com.theyoseph.DemoSupermercado.exception;

import com.theyoseph.DemoSupermercado.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(
            NotFoundException e,
            HttpServletRequest r
    ){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error("Recurso no encontrado")
                .message(e.getMessage())
                .path(r.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ParamsException.class)
    public ResponseEntity<ErrorResponseDTO> handleParams(
            ParamsException e,
            HttpServletRequest r
    ){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Parámetros inválidos")
                .message(e.getMessage())
                .path(r.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoData(
            NoDataException e,
            HttpServletRequest r
    ){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .error("Sin Datos")
                .message(e.getMessage())
                .path(r.getRequestURI())
                .build();
        // IMPORTANTE:
        // ResponseEntity con 204 NO puede llevar body
        return ResponseEntity.status(HttpStatus.OK).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneral(
            Exception e,
            HttpServletRequest r
    ){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Error interno del servidor")
                .message(e.getMessage())
                .path(r.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
