package com.alexalins.bagdex.service;

import com.alexalins.bagdex.domain.dto.TreinadorDTO;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.exception.EmailAlreadyExistsException;
import com.alexalins.bagdex.repository.TreinadorRepository;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinadorService {
    @Autowired
    private TreinadorRepository treinadorRepository;

    public List<TreinadorDTO> getTreinadores() {
        List<Treinador> list = treinadorRepository.findAll();
        System.out.println(list);
        return list.stream().map(TreinadorDTO::create).collect(Collectors.toList());
    }

    public TreinadorDTO getTreinadorByEmail(String email) {
        Treinador treinador = treinadorRepository.findByEmail(email);
        if(treinador == null) {
            return null;
        }
        return TreinadorDTO.create(treinador);
    }

    public TreinadorDTO save(Treinador treinador) {
        if(getTreinadorByEmail(treinador.getEmail()) != null) {
            throw new EmailAlreadyExistsException("E-mail já cadastrado!");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        treinador.setSenha(encoder.encode(treinador.getSenha()));
        return TreinadorDTO.create(treinadorRepository.save(treinador));
    }
}
