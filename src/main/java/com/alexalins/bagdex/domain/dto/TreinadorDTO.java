package com.alexalins.bagdex.domain.dto;

import com.alexalins.bagdex.domain.model.Treinador;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreinadorDTO {
    private Long id;
    private String nome;
    private String email;
    private String token;

    public static TreinadorDTO create(Treinador treinador) {
        ModelMapper modelMapper = new ModelMapper();
        TreinadorDTO dto = modelMapper.map(treinador, TreinadorDTO.class);
        return dto;
    }

    public static TreinadorDTO create(Treinador treinador, String token) {
        TreinadorDTO dto = create(treinador);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}