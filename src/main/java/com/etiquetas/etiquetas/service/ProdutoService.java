package com.etiquetas.etiquetas.service;

import com.etiquetas.etiquetas.model.Produto;
import com.etiquetas.etiquetas.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public Produto criar(Produto produto) throws NegocioException {
        if (produtoRepository.existsByCodigoBarras(produto.getCodigoBarras())) {
            throw new NegocioException("Código de barras já cadastrado");
        }
        return produtoRepository.save(produto);
    }
}
