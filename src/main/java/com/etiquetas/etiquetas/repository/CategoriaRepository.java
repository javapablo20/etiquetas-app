package com.etiquetas.etiquetas.repository;

import com.etiquetas.etiquetas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade Categoria.
 * Estende JpaRepository para fornecer operações CRUD e consultas padrão.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    /**
     * Verifica se já existe uma categoria com o nome fornecido.
     *
     * @param nome nome da categoria a ser verificada.
     * @return true se uma categoria com o nome existir, false caso contrário.
     */
    boolean existsByNome(String nome);
}
