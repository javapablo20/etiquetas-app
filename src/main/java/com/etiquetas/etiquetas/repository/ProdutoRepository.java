package com.etiquetas.etiquetas.repository;

import com.etiquetas.etiquetas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade Produto.
 * Estende JpaRepository, o que fornece operações CRUD básicas para a entidade Produto.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    /**
     * Verifica se já existe um produto no banco de dados com o código de barras fornecido.
     */
    boolean existsByCodigoBarras(String codigoBarras);
}
