package com.etiquetas.etiquetas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * Entidade que representa um usuário no sistema.
 * Implementa a interface UserDetails para integração com o Spring Security.
 */
@Entity  // Indica que esta classe é uma entidade JPA, mapeada para uma tabela no banco de dados.
@Getter  // Lombok: gera automaticamente os métodos getters para todos os campos.
@Setter  // Lombok: gera automaticamente os métodos setters.
@NoArgsConstructor  // Lombok: gera um construtor sem argumentos.
@AllArgsConstructor  // Lombok: gera um construtor com todos os campos.
public class Usuario implements UserDetails {

    @Id  // Define a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID gerado automaticamente pelo banco.
    private Integer id;  // Identificador único do usuário.

    @NotBlank  // Valida que o nome não é nulo nem vazio.
    @Size(max = 100)  // Limita o tamanho do nome a no máximo 100 caracteres.
    private String nome;  // Nome completo do usuário.

    @NotBlank
    @Size(max = 50)
    private String login;  // Nome de login do usuário. Deve ser único no sistema.

    @NotBlank
    @Size(max = 100)
    private String senha;  // Senha do usuário (deve ser armazenada com criptografia).

    @Enumerated(EnumType.STRING)  // Armazena o valor enum como String no banco (ex: "ADMIN").
    @Size(max = 20)  // Limita o tamanho da string armazenada (opcional).
    private NivelAcesso nivelAcesso;  // Nível de acesso do usuário (ADMIN ou OPERADOR).

    private Timestamp dataCriacao;  // Timestamp da criação da conta.

    private Timestamp dataUltimoLogin;  // Timestamp do último login do usuário.

    /**
     * Retorna a lista de autoridades do usuário com base no seu nível de acesso.
     * Spring Security utiliza isso para controle de acesso.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + nivelAcesso.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    // Os métodos abaixo são necessários para a implementação do UserDetails.
    @Override
    public boolean isAccountNonExpired() {
        return true;  // Indica que a conta nunca expira.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Indica que a conta nunca é bloqueada.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Indica que as credenciais nunca expiram.
    }

    @Override
    public boolean isEnabled() {
        return true;  // Indica que a conta está ativa.
    }

    /**
     * Enum que representa os níveis de acesso possíveis no sistema.
     */
    public enum NivelAcesso {
        ADMIN, OPERADOR
    }
}