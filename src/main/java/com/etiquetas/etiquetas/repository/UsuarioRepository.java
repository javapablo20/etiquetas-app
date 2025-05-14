package com.etiquetas.etiquetas.repository;

import com.etiquetas.etiquetas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface de repositório para a entidade Usuario.
 * Fornece acesso a métodos de persistência e busca personalizada.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca um usuário com base no login.
     *
     * @param login login do usuário.
     * @return um Optional contendo o usuário, caso exista.
     */
    Optional<Usuario> findByLogin(String login);
}
