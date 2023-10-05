package com.alexalins.bagdex.service;

import com.alexalins.bagdex.domain.dto.TreinadorDTO;
import com.alexalins.bagdex.domain.model.Role;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.exception.EmailAlreadyExistsException;
import com.alexalins.bagdex.repository.RoleRepository;
import com.alexalins.bagdex.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreinadorService implements UserDetailsService {
    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<TreinadorDTO> getTreinadores() {
        List<Treinador> list = treinadorRepository.findAll();
        return list.stream().map(TreinadorDTO::create).collect(Collectors.toList());
    }

    public TreinadorDTO getTreinadorByEmail(String email) {
        Treinador treinador = treinadorRepository.findByEmail(email);
        if(treinador == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o nome de usuário: " + email);
        }
        return TreinadorDTO.create(treinador);
    }

    public TreinadorDTO save(Treinador treinador) {
        if(getTreinadorByEmail(treinador.getEmail()) != null) {
            throw new EmailAlreadyExistsException("E-mail já cadastrado!");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        treinador.setSenha(encoder.encode(treinador.getSenha()));
        Role role = roleRepository.getById(1l);
        treinador.setRoles(Arrays.asList(role));
        return TreinadorDTO.create(treinadorRepository.save(treinador));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Treinador treinador = treinadorRepository.findByEmail(username);
        if(treinador == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o nome de usuário: " + username);
        }
        return treinador;
    }
}
