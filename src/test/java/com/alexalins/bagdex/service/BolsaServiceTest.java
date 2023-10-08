package com.alexalins.bagdex.service;

import com.alexalins.bagdex.domain.dto.BolsaDTO;
import com.alexalins.bagdex.domain.dto.TreinadorDTO;
import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Tipo;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.exception.EmailAlreadyExistsException;
import com.alexalins.bagdex.repository.BolsaRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
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
        bolsa.setNome("Bolsa teste");
        bolsa.setDescricao("Bolsa teste");
        bolsa.setTipo(Tipo.JA_TENHO);
        bolsa.setPokemon(new ArrayList<>());
        //
        listBolsas = new ArrayList<>();
        listBolsas.add(bolsa);
    }

    @Test
    public void testSalvarBolsa() {
        when(bolsaRepository.save(any())).thenReturn(bolsa);
        BolsaDTO savedBolsa = bolsaService.save(bolsa);
        assertNotNull(savedBolsa);
    }

    @Test
    public void testGetBolsas() {
        when(bolsaRepository.findAll()).thenReturn(listBolsas);
        List<BolsaDTO> myBolsas = bolsaService.getBolsas();
        assertNotNull(myBolsas);
    }

    @Test
    public void getBolsaId() {
        Optional<Bolsa> opBolsa = Optional.of(bolsa);
        when(bolsaRepository.findById(1L)).thenReturn(opBolsa);
        BolsaDTO myBolsa = bolsaService.getBolsaId(1L);
        assertEquals(bolsa.getNome(), myBolsa.getNome());
    }


    @Test
    public void getBolsaPorTreinadorId() {
        Treinador treinador = new Treinador();
        treinador.setId(1L);
        treinador.setNome("Teste");
        treinador.setEmail("test@example.com");
        List<Bolsa> bolsas = new ArrayList<>();
        when(bolsaRepository.findByTreinadorId(treinador.getId())).thenReturn(bolsas);
        List<BolsaDTO> myBolsas = bolsaService.getBolsaPorTreinadorId(treinador);
        assertEquals(bolsas.size(), myBolsas.size());
    }
}
