package com.alexalins.bagdex.controller;

import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.domain.util.JsonUtil;
import com.alexalins.bagdex.repository.TreinadorRepository;
import com.alexalins.bagdex.service.TreinadorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class TreinadorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private Treinador treinador;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        treinador = new Treinador();
        treinador.setNome("Teste");
        treinador.setEmail("test@example.com");
        treinador.setSenha("password");
    }

    @Test
    public void testGetTreinadores() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/treinador/lista"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testSaveTreinador() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/treinador/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(treinador)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetTreinador() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/treinador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(treinador)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
