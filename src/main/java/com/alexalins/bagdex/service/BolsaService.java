package com.alexalins.bagdex.service;

import com.alexalins.bagdex.domain.dto.BolsaDTO;
import com.alexalins.bagdex.domain.dto.BolsaTreinadorDTO;
import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.domain.util.DataUtil;
import com.alexalins.bagdex.repository.BolsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BolsaService {
    @Autowired
    private BolsaRepository bolsaRepository;

    public List<BolsaDTO> getBolsas() {
        List<Bolsa> list = bolsaRepository.findAll();
        return list.stream().map(BolsaDTO::create).collect(Collectors.toList());
    }

    public List<BolsaTreinadorDTO> getBolsaPorTreinadorId(Treinador treinador) {
        if(treinador == null) {
            return null;
        }
        List<Bolsa> bolsas = bolsaRepository.findByTreinadorId(treinador.getId());
        Comparator<Bolsa> comparadorDataDecrescente = new Comparator<Bolsa>() {
            @Override
            public int compare(Bolsa bolsa1, Bolsa bolsa2) {
                return bolsa2.getData().compareTo(bolsa1.getData());
            }
        };

        Collections.sort(bolsas, comparadorDataDecrescente);
        return bolsas.stream().map(BolsaTreinadorDTO::create).collect(Collectors.toList());
    }

    public BolsaDTO save(Bolsa bolsa) {
        bolsa.setData(DataUtil.getCurrentDate());
        BolsaDTO dto =  BolsaDTO.create(bolsaRepository.save(bolsa));
        return dto;
    }

    public BolsaDTO getBolsaId(Long id) {
        Optional<Bolsa> getBolsa = bolsaRepository.findById(id);
        if(!getBolsa.isPresent()) {
            throw new EmptyResultDataAccessException("Bolsa não existe", 0);
        }

        return BolsaDTO.create(getBolsa.get());
    }

    public BolsaDTO update(Long id, Bolsa bolsa) {
        Optional<Bolsa> b = bolsaRepository.findById(id);
        if(b.isPresent()) {
            Bolsa myBag = new Bolsa();
            myBag.setId(bolsa.getId());
            myBag.setNome(bolsa.getNome());
            myBag.setDescricao(bolsa.getDescricao());
            myBag.setTipo(bolsa.getTipo());
            myBag.setTreinador(bolsa.getTreinador());
            myBag.setPokemon(bolsa.getPokemon());
            myBag.setData(DataUtil.getCurrentDate());
            return BolsaDTO.create(bolsaRepository.save(myBag));
        } else  {
            throw new EmptyResultDataAccessException("Bolsa não existe", 0);
        }
    }
}
