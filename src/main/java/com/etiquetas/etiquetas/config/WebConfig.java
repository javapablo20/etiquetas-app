package com.etiquetas.etiquetas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuração do CORS (Cross-Origin Resource Sharing) para permitir a comunicação
 * entre o frontend e o backend, especialmente quando estão em origens diferentes.
 * Configura as permissões de origem e os métodos HTTP permitidos para o acesso à API.
 */
@Configuration  // Indica que esta classe contém configurações de Spring.
public class WebConfig {

    /**
     * Método que configura o CORS para permitir requisições de origens específicas.
     * O CORS é utilizado para garantir que o frontend e o backend possam se comunicar
     * mesmo estando em domínios diferentes.
     *
     * @return uma configuração personalizada de CORS.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configura o CORS permitindo requisições de http://localhost:4200 (frontend Angular)
                registry.addMapping("/**")  // Aplica CORS para todas as rotas da API.
                        .allowedOrigins("http://localhost:4200")  // Permite o frontend Angular.
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Permite os métodos HTTP específicos.
                        .allowedHeaders("Authorization", "Content-Type")  // Permite os cabeçalhos necessários.
                        .exposedHeaders("Authorization")  // Expor o cabeçalho 'Authorization' na resposta.
                        .allowCredentials(true);  // Permite o envio de credenciais (como cookies ou tokens).
            }
        };
    }
}