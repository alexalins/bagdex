package com.alexalins.bagdex.controller;

import com.alexalins.bagdex.domain.dto.BolsaDTO;
import com.alexalins.bagdex.domain.dto.BolsaTreinadorDTO;
import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Treinador;
import com.alexalins.bagdex.service.BolsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bolsa")
public class BolsaController {

    @Autowired
    private BolsaService bolsaService;

    @GetMapping("/lista")
    public ResponseEntity getListaDeBolsas() {
        List<BolsaDTO> list = bolsaService.getBolsas();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/salvar")
    public ResponseEntity save(@RequestBody Bolsa bolsa) {
        BolsaDTO bolsaDto = bolsaService.save(bolsa);
        return ResponseEntity.created(getUri(bolsaDto.getId())).build();
    }

    @PostMapping("/treinador")
    public ResponseEntity getBolsaByTreinador(@RequestBody Treinador treinador) {
        List<BolsaTreinadorDTO> list = bolsaService.getBolsaPorTreinadorId(treinador);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBolsaById(@PathVariable("id") Long id) {
        BolsaDTO bolsaDto = bolsaService.getBolsaId(id);
        return ResponseEntity.ok(bolsaDto);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
                                 @RequestBody Bolsa bolsa) {
        bolsa.setId(id);
        BolsaDTO bolsaDto = bolsaService.update(id, bolsa);
        return ResponseEntity.created(getUri(bolsaDto.getId())).build();
    }
}
