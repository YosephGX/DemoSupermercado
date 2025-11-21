package com.theyoseph.DemoSupermercado.exception;

import com.theyoseph.DemoSupermercado.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Metodo genérico para crear ErrorResponseDTO
    private ResponseEntity<ErrorResponseDTO> buildError(
            HttpStatus status,
            String error,
            Exception message,
            HttpServletRequest request
    ){
        ErrorResponseDTO dto = ErrorResponseDTO.builder()
                .status(status.value())
                .error(error)
                .message(message.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .code(String.valueOf(status.value()))
                .build();
        return ResponseEntity.status(status).body(dto);
    }

    private ResponseEntity<ErrorResponseDTO> buildError(
            HttpStatus status,
            String error,
            Exception message,
            HttpServletRequest request,
            String code
    ){
        ErrorResponseDTO dto = ErrorResponseDTO.builder()
                .status(status.value())
                .error(error)
                .message(message.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .code(code)
                .build();
        return ResponseEntity.status(status).body(dto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(
            NotFoundException e,
            HttpServletRequest r
    ){
        return buildError(HttpStatus.NOT_FOUND, "Recurso no encontrado", e, r);
    }

    @ExceptionHandler(ParamsException.class)
    public ResponseEntity<ErrorResponseDTO> handleParams(
            ParamsException e,
            HttpServletRequest r
    ){
        return buildError(HttpStatus.BAD_REQUEST, "Parámetros inválidos", e, r);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoData(
            NoDataException e,
            HttpServletRequest r
    ){
        // ResponseEntity con 204 NO puede llevar body
        return buildError(HttpStatus.NOT_FOUND, "Sin Datos", e, r);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorized(
            UnauthorizedException e,
            HttpServletRequest r
    ){
        return buildError(HttpStatus.UNAUTHORIZED, "No Autorizado", e, r);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponseDTO> handleForbidden(
            ForbiddenException e,
            HttpServletRequest r
    ){
        return buildError(HttpStatus.FORBIDDEN, "Acceso denegado", e, r);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handleConflict(
            ConflictException e,
            HttpServletRequest r
    ){
        return buildError(HttpStatus.CONFLICT, "Recurso en conflicto", e, r);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneral(
            Exception e,
            HttpServletRequest r
    ){
        log.error("Error inesperado en: {}", r.getRequestURI(), e); // loggeamos el stacktrace
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", e, r);
    }
}
