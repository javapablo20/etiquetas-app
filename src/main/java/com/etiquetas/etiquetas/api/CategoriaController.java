package com.etiquetas.etiquetas.api;

import com.etiquetas.etiquetas.model.Categoria;
import com.etiquetas.etiquetas.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe de controle responsável por expor os endpoints relacionados às categorias.
 * Fornece operações para criação e listagem de categorias.
 *
 * A classe utiliza o padrão REST para a criação de uma API que interage com o sistema de gestão de categorias.
 */
@RestController  // Indica que esta classe é um controlador REST (responsável por manipular requisições HTTP).
@RequestMapping("/categorias")  // Define o caminho base para os endpoints desta classe.
@Tag(name = "Categorias", description = "Categorias")  // Adiciona a documentação OpenAPI para o Swagger.
@RequiredArgsConstructor  // Lombok: Gera automaticamente o construtor com os parâmetros obrigatórios.
public class CategoriaController {

    private final CategoriaService categoriaService;  // Injeção de dependência do serviço CategoriaService.

    /**
     * Endpoint para cadastrar uma nova categoria.
     * A categoria é validada antes de ser enviada ao serviço de criação.
     *
     * @param categoria - O objeto Categoria que será criado.
     * @return a categoria criada com todos os dados preenchidos, incluindo o ID gerado.
     */
    @PostMapping  // Mapeia a URL para requisições HTTP POST.
    @ResponseStatus(HttpStatus.CREATED)  // Define o código de status HTTP 201 (Criado) na resposta.
    @Operation(summary = "Nova categoria")  // Define a descrição da operação para o Swagger.
    public Categoria criar(@RequestBody @Valid Categoria categoria) {
        // Chama o serviço de criação de categorias e retorna a categoria criada.
        return categoriaService.criar(categoria);
    }

    /**
     * Endpoint para listar todas as categorias cadastradas no sistema.
     * Retorna uma lista de todas as categorias disponíveis.
     *
     * @return Lista de categorias.
     */
    @GetMapping  // Mapeia a URL para requisições HTTP GET.
    @Operation(summary = "Todas as categorias")  // Define a descrição da operação para o Swagger.
    public List<Categoria> listar() {
        // Chama o serviço de listagem de categorias e retorna a lista.
        return categoriaService.listar();
    }
}