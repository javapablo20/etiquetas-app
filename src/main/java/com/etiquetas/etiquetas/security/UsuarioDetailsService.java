package com.etiquetas.etiquetas.security;

import com.etiquetas.etiquetas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por carregar os detalhes do usuário a partir do banco de dados.
 * Implementa a interface UserDetailsService do Spring Security, que é usada para autenticação de usuários.
 * Este serviço busca o usuário no repositório usando o login e retorna um objeto UserDetails.
 */
@Service  // Indica que esta classe é um serviço e será gerenciada pelo Spring.
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;  // Repositório responsável pela interação com a tabela de usuários no banco de dados.

    /**
     * Carrega os detalhes do usuário a partir do banco de dados usando o login.
     * O método busca o usuário pelo login, e se não for encontrado, lança uma exceção.
     *
     * @param username o nome de usuário (login) fornecido para autenticação.
     * @return um objeto UserDetails que contém as informações do usuário.
     * @throws UsernameNotFoundException se o usuário não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo login.
        return usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));  // Lança exceção se não encontrar o usuário.
    }
}
