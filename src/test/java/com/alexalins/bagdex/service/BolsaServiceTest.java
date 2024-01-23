package com.alexalins.bagdex.service;

import com.alexalins.bagdex.domain.dto.BolsaDTO;
import com.alexalins.bagdex.domain.dto.BolsaTreinadorDTO;
import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Tipo;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.repository.BolsaRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BolsaServiceTest {

    @Autowired
    private BolsaService bolsaService;

    @Mock
    private BolsaRepository bolsaRepository;

    private Treinador treinador;

    private Bolsa bolsa;

    private List<Bolsa> listBolsas;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //
        treinador = new Treinador();
        treinador.setId(1L);
        treinador.setNome("Teste");
        treinador.setEmail("test@example.com");
        //
        bolsa = new Bolsa();
        bolsa.setId(1l);
        bolsa.setNome("Bolsa teste");
        bolsa.setDescricao("Bolsa teste");
        bolsa.setTipo(Tipo.JA_TENHO);
        bolsa.setPokemon(new ArrayList<>());
        //
        listBolsas = new ArrayList<>();
        listBolsas.add(bolsa);
    }

    @Test
    @Order(1)
    public void testSalvarBolsa() {
        when(bolsaRepository.save(any())).thenReturn(bolsa);
        BolsaDTO savedBolsa = bolsaService.save(bolsa);
        assertNotNull(savedBolsa);
    }

    @Test
    @Order(2)
    public void testGetBolsas() {
        when(bolsaRepository.findAll()).thenReturn(listBolsas);
        List<BolsaDTO> myBolsas = bolsaService.getBolsas();
        assertNotNull(myBolsas);
    }

    @Test
    @Order(3)
    public void getBolsaId() {
        Optional<Bolsa> opBolsa = Optional.of(bolsa);
        when(bolsaRepository.findById(1L)).thenReturn(opBolsa);
        BolsaDTO myBolsa = bolsaService.getBolsaId(1L);
        assertEquals(bolsa.getNome(), myBolsa.getNome());
    }

    @Test
    public void getBolsaIdErro() {
        when(bolsaRepository.findById(1L)).thenReturn(null);
        EmptyResultDataAccessException result = assertThrows(EmptyResultDataAccessException.class, () -> bolsaService.getBolsaId(0l));
        assertTrue(result.getMessage().contains("Bolsa não existe!"));
    }

    @Test
    @Order(4)
    public void getBolsaPorTreinadorId() {
        Treinador treinador = new Treinador();
        treinador.setId(1L);
        treinador.setNome("Teste");
        treinador.setEmail("test@example.com");
        List<Bolsa> bolsas = new ArrayList<>();
        when(bolsaRepository.findByTreinadorId(treinador.getId())).thenReturn(bolsas);
        List<BolsaTreinadorDTO> myBolsas = bolsaService.getBolsaPorTreinadorId(treinador);
        assertEquals(bolsas.size(), myBolsas.size());
    }

    @Test
    public void getBolsaPorTreinadorIdErro() {
        when(bolsaRepository.findByTreinadorId(treinador.getId())).thenReturn(null);
        EmptyResultDataAccessException result = assertThrows(EmptyResultDataAccessException.class, () -> bolsaService.getBolsaPorTreinadorId(null));
        assertTrue(result.getMessage().contains("Treinador não existe!"));
    }

    @Test
    @Order(5)
    public void testEditarBolsa() {
        bolsa.setId(1l);
        Optional<Bolsa> opBolsa = Optional.of(bolsa);
        when(bolsaRepository.findById(1L)).thenReturn(opBolsa);
        when(bolsaRepository.save(any())).thenReturn(bolsa);
        BolsaDTO updatedBolsa = bolsaService.update(bolsa.getId(), bolsa);
        assertNotNull(updatedBolsa);
    }

    @Test
    public void testEditarBolsaErro() {
        when(bolsaRepository.findById(1L)).thenReturn(null);
        EmptyResultDataAccessException result = assertThrows(EmptyResultDataAccessException.class, () -> bolsaService.update(0l, null));
        assertTrue(result.getMessage().contains("Bolsa não existe!"));
    }

    @Test
    @Order(6)
    public void testDeletarBolsa() {
        //salvando
        when(bolsaRepository.save(any())).thenReturn(bolsa);
        bolsaService.save(bolsa);
        //
        bolsa.setId(1l);
        Optional<Bolsa> opBolsa = Optional.of(bolsa);
        when(bolsaRepository.findById(1L)).thenReturn(opBolsa);
        boolean result = bolsaService.delete(bolsa.getId());
        assertTrue(result);
    }

    @Test
    public void testDeletarBolsaErro() {
        when(bolsaRepository.findById(1L)).thenReturn(null);
        EmptyResultDataAccessException result = assertThrows(EmptyResultDataAccessException.class, () -> bolsaService.delete(1l));
        assertTrue(result.getMessage().contains("Bolsa não existe!"));
    }
}
