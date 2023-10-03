package com.alexalins.bagdex.service;

import com.alexalins.bagdex.domain.dto.BolsaDTO;
import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.domain.util.DataUtil;
import com.alexalins.bagdex.repository.BolsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BolsaService {
    @Autowired
    private BolsaRepository bolsaRepository;

    public List<BolsaDTO> getBolsas() {
        List<Bolsa> list = bolsaRepository.findAll();
        return list.stream().map(BolsaDTO::create).collect(Collectors.toList());
    }

    public BolsaDTO getBolsaPorTreinadorId(Treinador treinador) {

        if(treinador == null) {
            return null;
        }

        Bolsa bolsa = bolsaRepository.findByTreinadorId(treinador.getId());

        return BolsaDTO.create(bolsa);
    }

    public BolsaDTO save(Bolsa bolsa) {
        System.out.println(bolsa);
        Date sqlDate = new Date(DataUtil.getCurrentDate().getTime());
        bolsa.setData(sqlDate);
        System.out.println(bolsa);
        BolsaDTO dto =  BolsaDTO.create(bolsaRepository.save(bolsa));
        System.out.println(dto.toString());
        return dto;
    }

    public BolsaDTO getBolsaId(Long id) {
        Optional<Bolsa> getBolsa = bolsaRepository.findById(id);
        if(!getBolsa.isPresent()) {
            throw new EmptyResultDataAccessException("Bolsa não existe", 0);
        }

        return BolsaDTO.create(getBolsa.get());
    }
}