package com.example.MsVenta.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
/**
 * Clase para manejar excepciones globalmente en la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja errores de validación para campos anotados con @NotEmpty, @Size, etc.
     * @param ex Excepción de validación.
     * @return Respuesta con detalles de los errores de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.error("Errores de validación: {}", errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones personalizadas de recursos no encontrados.
     * @param ex Excepción de recurso no encontrado.
     * @return Respuesta con el mensaje de error.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.warn("Recurso no encontrado: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja errores de tipo de argumento (por ejemplo, si se espera un Long y se pasa una String).
     * @param ex Excepción de tipo de argumento.
     * @return Respuesta con el mensaje de error.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = "Tipo de argumento inválido para el parámetro '" + ex.getName() + "'. Se esperaba " + ex.getRequiredType().getSimpleName();
        logger.error("Error de tipo de argumento: {}", error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones generales.
     * @param ex Excepción genérica.
     * @return Respuesta con un mensaje genérico de error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("Error interno del servidor: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Error interno del servidor. Por favor, intenta nuevamente más tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
