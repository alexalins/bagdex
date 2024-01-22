package com.alexalins.bagdex.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexalins.bagdex.domain.dto.TreinadorDTO;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.exception.EmailAlreadyExistsException;
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
        treinador.setSenha("teste");
        //
        listTreinador = new ArrayList<>();
        listTreinador.add(treinador);
    }

    @Test
    public void testSaveUser() {
        when(treinadorRepository.findByEmail(anyString())).thenReturn(null);
        when(treinadorRepository.save(any())).thenReturn(treinador);
        TreinadorDTO savedUser = treinadorService.save(treinador);

        assertNotNull(savedUser);
    }

    @Test
    public void testSaveUserErro() {
        when(treinadorRepository.findByEmail(anyString())).thenReturn(new Treinador());
        EmailAlreadyExistsException result = assertThrows(EmailAlreadyExistsException.class, () -> treinadorService.save(treinador));
        assertTrue(result.getMessage().contains("E-mail já cadastrado!"));
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