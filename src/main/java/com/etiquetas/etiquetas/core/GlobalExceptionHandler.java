package com.etiquetas.etiquetas.core;

import com.etiquetas.etiquetas.service.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que trata as exceções de forma global na aplicação.
 * Utiliza o @RestControllerAdvice para fornecer um tratamento centralizado de exceções.
 */
@RestControllerAdvice  // Indica que esta classe irá interceptar exceções lançadas por controladores REST.
public class GlobalExceptionHandler {

    /**
     * Manipula exceções de validação (MethodArgumentNotValidException) lançadas quando falham as anotações de validação dos modelos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();  // Cria um mapa para armazenar os erros de validação.
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));  // Mapeia os erros para o nome do campo e a mensagem de erro.
        return ResponseEntity.badRequest().body(errors);  // Retorna os erros como uma resposta HTTP 400 (Bad Request).
    }

    /**
     * Manipula exceções de negócios (NegocioException) lançadas durante o processo de validação de regras de negócio.
     */
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> handleNegocioException(NegocioException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());  // Retorna a mensagem de erro da exceção.
    }
}
