package com.alexalins.bagdex.controller;

import com.alexalins.bagdex.domain.dto.TreinadorDTO;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.security.jwt.request.JwtRequest;
import com.alexalins.bagdex.security.jwt.JwtTokenUtil;
import com.alexalins.bagdex.security.jwt.response.JwtResponse;
import com.alexalins.bagdex.service.TreinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TreinadorService treinadorService;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());

        final UserDetails userDetails = treinadorService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity save(@RequestBody Treinador treinador) {
        TreinadorDTO u = treinadorService.save(treinador);
        return ResponseEntity.created(getUri(u.getId())).build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciais inválidas", e);
        }
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
