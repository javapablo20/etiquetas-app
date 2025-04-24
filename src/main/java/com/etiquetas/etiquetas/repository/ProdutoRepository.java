package com.etiquetas.etiquetas.repository;

import com.etiquetas.etiquetas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByCodigoBarras(String codigoBarras);
}
