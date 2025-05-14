package com.etiquetas.etiquetas.service;

import java.util.List;

import com.etiquetas.etiquetas.model.Produto;
import com.etiquetas.etiquetas.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço que contém a lógica de negócios para a criação e listagem de produtos.
 * A transação é garantida durante o processo de criação.
 */
@Service  // Indica que esta classe é um serviço e será gerenciada pelo Spring.
@RequiredArgsConstructor  // Lombok: Gera o construtor com os parâmetros obrigatórios.
public class ProdutoService {

    private final ProdutoRepository produtoRepository;  // Injeção de dependência do repositório ProdutoRepository.

    /**
     * Cria um novo produto no banco de dados.
     * Antes de salvar, verifica se o código de barras já está cadastrado.
     * Se o código de barras já existir, lança uma exceção de negócio.
     *
     * @param produto o produto a ser criado.
     * @return o produto salvo no banco de dados.
     * @throws NegocioException se o código de barras já estiver cadastrado.
     */
    @Transactional  // Garante que a operação de criação será feita dentro de uma transação.
    public Produto criar(Produto produto) throws NegocioException {
        // Verifica se o código de barras do produto já está registrado.
        if (produtoRepository.existsByCodigoBarras(produto.getCodigoBarras())) {
            throw new NegocioException("Código de barras já cadastrado");  // Lança exceção se o código de barras já existir.
        }

        return produtoRepository.save(produto);  // Persiste o novo produto no banco de dados.
    }

    /**
     * Retorna uma lista de todos os produtos.
     *
     * @return uma lista com todos os produtos cadastrados no banco de dados.
     */
    public List<Produto> listar() {
        return produtoRepository.findAll();  // Retorna todos os produtos do banco de dados.
    }
}
