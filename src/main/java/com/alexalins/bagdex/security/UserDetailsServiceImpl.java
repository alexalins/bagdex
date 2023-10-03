package com.alexalins.bagdex.security;

import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Treinador treinador = treinadorRepository.findByEmail(username);
        System.out.println(treinador);
        if(treinador == null) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return treinador;
    }
}
