package com.alexalins.bagdex.domain.dto;

import com.alexalins.bagdex.domain.model.Pokemon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PokemonDTO {
    private Long id;
    private String nome;
    private String foto;

    public static PokemonDTO create(Pokemon pokemon) {
        ModelMapper modelMapper = new ModelMapper();
        PokemonDTO dto = modelMapper.map(pokemon, PokemonDTO.class);
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}
