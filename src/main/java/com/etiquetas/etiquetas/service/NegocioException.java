package com.etiquetas.etiquetas.service;

/**
 * Exceção personalizada para erros de negócios.
 * É lançada quando há uma violação das regras de negócios da aplicação.
 */
public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);  // Passa a mensagem de erro para a classe pai.
    }
}
