package com.alexalins.bagdex.controller;

import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Pokemon;
import com.alexalins.bagdex.domain.model.Tipo;
import com.alexalins.bagdex.domain.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class BolsaControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private Bolsa bolsa;

    private String treinadorGet;

    private Pokemon pokemon;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        bolsa = new Bolsa();
        bolsa.setNome("Bolsa teste");
        bolsa.setDescricao("Bolsa teste");
        bolsa.setTipo(Tipo.JA_TENHO);

        treinadorGet = " {\n" +
                "        \"id\": 1,\n" +
                "        \"nome\": \"teste\",\n" +
                "        \"email\": \"teste@teste.com\"\n" +
                "    }";

        pokemon = new Pokemon();
        pokemon.setId(1l);
        pokemon.setNome("Pikachu");
        pokemon.setFoto("urlfoto/pikachu");
    }

    @Test
    public void testGetBolsas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bolsa/lista"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testSalvarBolsa() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bolsa/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(bolsa)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetTreinador() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bolsa/treinador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(bolsa)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetBolsaById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bolsa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(treinadorGet))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateBolsa() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/bolsa/editar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(bolsa)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testDeleteBolsa() throws Exception {
        //salva
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bolsa/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(bolsa)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        //deleta
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/bolsa/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void savePokemon() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/bolsa/save/pokemon/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(pokemon)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
