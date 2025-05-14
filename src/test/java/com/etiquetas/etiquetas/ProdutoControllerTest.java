package com.etiquetas.etiquetas;

import com.etiquetas.etiquetas.model.Produto;
import com.etiquetas.etiquetas.service.ProdutoService;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class ProdutoServiceMockConfig {
        @Bean
        ProdutoService produtoService() {
            return Mockito.mock(ProdutoService.class);
        }
    }

    @Autowired
    private ProdutoService produtoService;

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void retornar201() throws Exception {
        Produto produto = new Produto();
        produto.setCodigoBarras("1234567891234");
        produto.setNome("Teste");
        produto.setPreco(BigDecimal.TEN);

        Mockito.when(produtoService.criar(Mockito.any())).thenReturn(produto);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"codigoBarras": "1234567891234",
                        "nome": "Teste",
                        "preco": 10.00,
                        "categoria": {
                        "id": 1
                                }
                        }
                        """)
        ).andExpect(status().isCreated());
    }
}