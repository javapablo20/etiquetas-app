package com.etiquetas.etiquetas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe que representa um Produto no sistema.
 * Mapeada para a tabela 'produto' no banco de dados.
 * Utiliza validações de dados e o padrão Lombok para reduzir código repetitivo.
 */
@Entity  // Indica que esta classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados.
@Getter  // Gera automaticamente os getters para os campos.
@Setter  // Gera automaticamente os setters para os campos.
@NoArgsConstructor  // Gera o construtor sem parâmetros.
@AllArgsConstructor  // Gera o construtor com todos os parâmetros.
public class Produto {

    @Id  // Indica que o campo 'id' será a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // A estratégia de geração do ID é o autoincremento.
    private Integer id;  // Identificador único do produto. Será gerado automaticamente pelo banco.

    @NotBlank  // Valida que o campo não pode ser nulo ou vazio.
    @Column(unique = true)  // Indica que o código de barras deve ser único na tabela.
    private String codigoBarras;  // Código de barras único para identificar o produto.

    @NotBlank  // Valida que o nome do produto não pode ser nulo ou vazio.
    @Size(max = 200)  // Limita o tamanho máximo do nome a 200 caracteres.
    private String nome;  // Nome do produto, com uma limitação de 200 caracteres.

    @Size(max = 500)  // Limita o tamanho máximo da descrição a 500 caracteres.
    private String descricao;  // Descrição do produto (opcional).

    @Positive  // Valida que o valor de 'preco' deve ser um número positivo.
    @NotNull  // Valida que o preço não pode ser nulo.
    private BigDecimal preco;  // Preço do produto.

    @ManyToOne  // Indica um relacionamento muitos-para-um com a classe Categoria.
    @JoinColumn(name = "categoria_id")  // A coluna 'categoria_id' é responsável por armazenar o ID da categoria do produto.
    private Categoria categoria;  // Relacionamento com a categoria do produto.

    private LocalDate dataValidade;  // Data de validade do produto (opcional).

    private LocalDate dataCadastro;  // Data de cadastro do produto, geralmente preenchido automaticamente.
}
