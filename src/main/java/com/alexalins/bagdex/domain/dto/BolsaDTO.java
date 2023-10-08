package com.alexalins.bagdex.domain.dto;

import com.alexalins.bagdex.domain.model.Bolsa;
import com.alexalins.bagdex.domain.model.Pokemon;
import com.alexalins.bagdex.domain.model.Tipo;
import com.alexalins.bagdex.domain.model.Treinador;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hibernate.collection.internal.PersistentBag;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BolsaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Tipo tipo;
    private List<PokemonDTO> pokemon;
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
