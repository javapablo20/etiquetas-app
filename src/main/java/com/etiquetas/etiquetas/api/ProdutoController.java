package com.etiquetas.etiquetas.api;

import com.etiquetas.etiquetas.model.Produto;
import com.etiquetas.etiquetas.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Classe de controle responsável por expor os endpoints relacionados a produtos.
 * Inclui a operação de criação de um produto, utilizando o serviço ProdutoService.
 */
@RestController  // Indica que esta classe é um controlador REST (responde a requisições HTTP).
@RequestMapping("/produtos")  // Define a URL base para todos os endpoints nesta classe.
@Tag(name = "Produtos", description = "Gestão de produtos")  // Define a documentação OpenAPI para o Swagger.
@RequiredArgsConstructor  // Lombok: Gera o construtor com parâmetros obrigatórios.
public class ProdutoController {

    private final ProdutoService produtoService;  // Injeção de dependência do serviço ProdutoService.

    @PostMapping  // Define que o método abaixo será responsável por requisições HTTP POST.
    @ResponseStatus(HttpStatus.CREATED)  // Define o código de status HTTP 201 (Criado) na resposta.
    @Operation(summary = "Cadastra um novo produto")  // Define a descrição da operação para o Swagger.
    public Produto criar(@RequestBody @Valid Produto produto) {
        return produtoService.criar(produto);  // Chama o serviço para criar o produto.
    }
}
