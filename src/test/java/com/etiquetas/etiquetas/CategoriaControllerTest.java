package com.etiquetas.etiquetas;

import com.etiquetas.etiquetas.model.Categoria;
import com.etiquetas.etiquetas.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class CategoriaServiceMockConfig {
        @Bean
        CategoriaService categoriaService() {
            return Mockito.mock(CategoriaService.class);
        }
    }

    @Autowired
    private CategoriaService categoriaService;

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void retornar201() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setNome("Teste");

        Mockito.when(categoriaService.criar(Mockito.any())).thenReturn(categoria);

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"nome": "Teste"}
                        """))
                .andExpect(status().isCreated());
    }
}