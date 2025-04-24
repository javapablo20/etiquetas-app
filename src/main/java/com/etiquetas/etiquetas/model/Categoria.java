package com.etiquetas.etiquetas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa uma Categoria de Produto no sistema.
 * Mapeada para a tabela 'categoria' no banco de dados.
 */
@Entity  // Indica que esta classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados.
@Getter  // Gera automaticamente os getters para os campos.
@Setter  // Gera automaticamente os setters para os campos.
public class Categoria {

    @Id  // Indica que o campo 'id' será a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // A estratégia de geração do ID é o autoincremento.
    private Integer id;  // Identificador único da categoria. Será gerado automaticamente pelo banco.

    @NotBlank  // Valida que o campo 'nome' não pode ser nulo ou vazio.
    @Column(unique = true)  // Garante que o nome da categoria seja único.
    private String nome;  // Nome da categoria.

    private String descricao;  // Descrição opcional da categoria.
}
