package com.alexalins.bagdex.domain.dto;

import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Tipo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.sql.Date;

@Data
public class BolsaTreinadorDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Tipo tipo;
    private Date data;

    public static BolsaTreinadorDTO create(Bolsa bolsa) {
        ModelMapper modelMapper = new ModelMapper();
        BolsaTreinadorDTO dto = modelMapper.map(bolsa, BolsaTreinadorDTO.class);
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}
