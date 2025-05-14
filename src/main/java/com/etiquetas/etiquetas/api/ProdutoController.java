package com.etiquetas.etiquetas.api;

import java.util.List;
import com.etiquetas.etiquetas.model.Produto;
import com.etiquetas.etiquetas.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Classe de controle responsável por expor os endpoints relacionados aos produtos.
 * Fornece as operações para criação, listagem e manipulação dos produtos.
 *
 * A classe utiliza o padrão REST para a criação de uma API que interage com o sistema de gestão de produtos.
 */
@RestController  // Indica que esta classe é um controlador REST (responsável por manipular requisições HTTP).
@RequestMapping("/produtos")  // Define o caminho base para os endpoints desta classe.
@Tag(name = "Produtos", description = "Gestão de produtos")  // Adiciona a documentação OpenAPI para o Swagger.
@RequiredArgsConstructor  // Lombok: Gera automaticamente o construtor com os parâmetros obrigatórios.
public class ProdutoController {

    private final ProdutoService produtoService;  // Injeção de dependência do serviço ProdutoService.

    /**
     * Endpoint para cadastrar um novo produto.
     * O produto é validado antes de ser enviado ao serviço de criação.
     *
     * @param produto - O objeto Produto que será criado.
     * @return o produto criado com todos os dados preenchidos, incluindo o ID gerado.
     */
    @PostMapping  // Mapeia a URL para requisições HTTP POST.
    @ResponseStatus(HttpStatus.CREATED)  // Define o código de status HTTP 201 (Criado) na resposta.
    @Operation(summary = "Cadastra um novo produto")  // Define a descrição da operação para o Swagger.
    public Produto criar(@RequestBody @Valid Produto produto) {
        // Chama o serviço de criação de produtos e retorna o produto criado.
        return produtoService.criar(produto);
    }

    /**
     * Endpoint para listar todos os produtos cadastrados no sistema.
     * Retorna uma lista de todos os produtos disponíveis.
     *
     * @return Lista de produtos.
     */
    @GetMapping  // Mapeia a URL para requisições HTTP GET.
    @Operation(summary = "Lista todos os produtos")  // Define a descrição da operação para o Swagger.
    public List<Produto> listar() {
        // Chama o serviço de listagem de produtos e retorna a lista.
        return produtoService.listar();
    }
}