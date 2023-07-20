package com.clinicaodontologica.MuelitasBlanquitas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarBadRequestException(BadRequestException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "🚨 Tienes un error: ".concat(exception.getMessage()));
        return mensaje;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> procesarResourceNotFoundException(ResourceNotFoundException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "🚨 No se pudo encontrar el recurso solicitado: ".concat(exception.getMessage()));
        return mensaje;
    }
}
