package com.alexalins.bagdex.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexalins.bagdex.domain.dto.TreinadorDTO;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.repository.TreinadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TreinadorServiceTest {

    @InjectMocks
    private TreinadorService treinadorService;

    @Mock
    private TreinadorRepository treinadorRepository;

    private List<Treinador> listTreinador;

    private Treinador treinador;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //
        treinador = new Treinador();
        treinador.setId(1L);
        treinador.setNome("Teste");
        treinador.setEmail("test@example.com");
        //
        listTreinador = new ArrayList<>();
        listTreinador.add(treinador);
    }

    @Test
    public void testSaveUser() {
        Treinador treinador = new Treinador();
        treinador.setNome("Teste");
        treinador.setEmail("test@example.com");
        treinador.setSenha("password");

        when(treinadorRepository.findByEmail(anyString())).thenReturn(null);
        when(treinadorRepository.save(any())).thenReturn(treinador);

        TreinadorDTO savedUser = treinadorService.save(treinador);

        assertNotNull(savedUser);
    }

    @Test
    public void testSaveUserErro() {
        Treinador treinador = new Treinador();
        treinador.setNome("Teste");
        treinador.setEmail("test@example.com");
        treinador.setSenha("password");

        when(treinadorRepository.findByEmail(anyString())).thenReturn(treinador);
        try {
            treinadorService.save(treinador);
            fail("Deveria ter lançado uma exceção de usuário existente");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Não foi possivel cadastrar, usuário já existe"));
        }
        verify(treinadorRepository, never()).save(any(Treinador.class));
    }

    @Test
    public void testGetUsers() {
        when(treinadorRepository.findAll()).thenReturn(listTreinador);

        List<TreinadorDTO> myUsers = treinadorService.getTreinadores();

        assertEquals(listTreinador.size(), myUsers.size());
    }

    @Test
    public void testGetUserByEmail() {
        when(treinadorRepository.findByEmail("test@example.com")).thenReturn(treinador);

        TreinadorDTO user = treinadorService.getTreinadorByEmail("test@example.com");

        assertNotNull(user);
    }
}