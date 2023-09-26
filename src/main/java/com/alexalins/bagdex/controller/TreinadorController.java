package com.alexalins.bagdex.controller;

import com.alexalins.bagdex.domain.dto.TreinadorDTO;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.service.TreinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class TreinadorController {

    @Autowired
    private TreinadorService treinadorService;

    @GetMapping()
    public ResponseEntity get() {
        List<TreinadorDTO> list = treinadorService.getTreinadores();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/info")
    public TreinadorDTO userInfo(@AuthenticationPrincipal Treinador treinador) {
        return TreinadorDTO.create(treinador);
    }

    @PostMapping("/cadastro")
    public ResponseEntity save(@RequestBody Treinador treinador) {
        TreinadorDTO u = treinadorService.save(treinador);
        return ResponseEntity.created(getUri(u.getId())).build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
