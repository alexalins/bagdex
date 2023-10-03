package com.alexalins.bagdex.domain.dto;

import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Pokemon;
import com.alexalins.bagdex.domain.model.Tipo;
import com.alexalins.bagdex.domain.model.Treinador;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.util.List;

@Data
public class BolsaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Tipo tipo;
    private List<Pokemon> pokemon;
    private Date data;
    private Treinador treinador;

    public static BolsaDTO create(Bolsa bolsa) {
        ModelMapper modelMapper = new ModelMapper();
        BolsaDTO dto = modelMapper.map(bolsa, BolsaDTO.class);
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}
