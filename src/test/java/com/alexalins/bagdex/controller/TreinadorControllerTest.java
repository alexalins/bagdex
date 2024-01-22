package com.alexalins.bagdex.controller;


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
public class TreinadorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String treinadorGet;

    private String treinadorSave;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        treinadorGet = " {\n" +
                "        \"id\": 1,\n" +
                "        \"nome\": \"teste\",\n" +
                "        \"email\": \"teste@teste.com\"\n" +
                "    }";

        treinadorSave = " {\n" +
                "        \"id\": 1,\n" +
                "        \"nome\": \"teste\",\n" +
                "        \"email\": \"teste@teste.com\",\n" +
                "        \"senha\": \"teste\"\n" +
                "    }";
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
                        .content(treinadorSave))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetTreinador() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/treinador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(treinadorGet))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
