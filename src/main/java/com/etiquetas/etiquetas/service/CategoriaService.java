package com.etiquetas.etiquetas.service;

import com.etiquetas.etiquetas.model.Categoria;
import com.etiquetas.etiquetas.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de serviço que contém a lógica de negócios para a criação e listagem de categorias.
 * Utiliza o repositório de Categoria para interagir com o banco de dados.
 */
@Service  // Indica que esta classe é um serviço e será gerenciada pelo Spring.
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;  // Repositório responsável pela interação com a tabela de categorias no banco de dados.

    /**
     * Construtor da classe CategoriaService.
     * Inicializa o repositório de categorias.
     *
     * @param categoriaRepository o repositório de categorias.
     */
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Cria uma nova categoria no banco de dados.
     * Antes de salvar, verifica se a categoria já existe (com base no nome).
     * Se já existir, lança uma exceção de negócio.
     *
     * @param categoria a categoria a ser criada.
     * @return a categoria salva no banco de dados.
     * @throws NegocioException se a categoria já existir.
     */
    @Transactional  // Garante que a operação de criação será feita dentro de uma transação.
    public Categoria criar(Categoria categoria) throws NegocioException {
        // Verifica se a categoria já existe no banco de dados, com base no nome.
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new NegocioException("Categoria já existe");  // Lança exceção se a categoria já existir.
        }
        return categoriaRepository.save(categoria);  // Persiste a nova categoria no banco de dados.
    }

    /**
     * Retorna uma lista de todas as categorias.
     *
     * @return uma lista com todas as categorias cadastradas no banco de dados.
     */
    public List<Categoria> listar() {
        return categoriaRepository.findAll();  // Retorna todas as categorias.
    }
}
