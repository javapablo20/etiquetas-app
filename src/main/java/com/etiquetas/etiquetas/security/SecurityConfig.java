package com.etiquetas.etiquetas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança da aplicação.
 * Define a configuração de autenticação e autorização dos endpoints, além de criar o gerenciador de autenticação e codificador de senhas.
 * Esta configuração permite que a aplicação utilize autenticação básica via JWT e controladores de acesso baseados em roles.
 */
@Configuration  // Indica que esta classe é uma configuração do Spring.
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;  // Serviço personalizado para carregar detalhes do usuário.

    /**
     * Construtor da classe SecurityConfig.
     *
     * @param usuarioDetailsService o serviço de detalhes do usuário.
     */
    public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }

    /**
     * Configura as regras de segurança da aplicação, incluindo as permissões de acesso aos endpoints.
     * Desativa CSRF, define o controle de acesso para diferentes URLs e configura a sessão para ser stateless.
     *
     * @param http configurações de segurança baseadas no HTTP.
     * @return a configuração do filtro de segurança.
     * @throws Exception caso ocorra um erro na configuração de segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Desativa a proteção contra CSRF (Cross-Site Request Forgery).
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()  // Permite o acesso irrestrito aos endpoints do Swagger.
                        .requestMatchers(HttpMethod.GET, "/categorias").permitAll()  // Permite acesso livre à listagem de categorias.
                        .requestMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")  // Somente usuários com a role ADMIN podem criar produtos.
                        .requestMatchers(HttpMethod.GET, "/produtos").authenticated()  // Requer autenticação para acessar a listagem de produtos.
                        .anyRequest().authenticated()  // Qualquer outro endpoint requer autenticação.
                )
                .userDetailsService(usuarioDetailsService)  // Utiliza o serviço de detalhes do usuário para autenticação.
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // A aplicação não mantém estado de sessão.
                .httpBasic(Customizer.withDefaults());  // Ativa autenticação HTTP básica.

        return http.build();  // Retorna a configuração do filtro de segurança.
    }

    /**
     * Define o codificador de senhas utilizado pela aplicação.
     * Utiliza o algoritmo BCrypt para codificar as senhas.
     *
     * @return o codificador de senha.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Retorna um codificador de senha BCrypt.
    }

    /**
     * Cria e retorna o AuthenticationManager.
     * O AuthenticationManager é responsável por validar as credenciais do usuário.
     *
     * @param config a configuração de autenticação.
     * @return o AuthenticationManager configurado.
     * @throws Exception caso ocorra um erro durante a criação do AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();  // Retorna o AuthenticationManager configurado.
    }
}
