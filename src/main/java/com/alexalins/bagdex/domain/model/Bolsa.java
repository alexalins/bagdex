package com.alexalins.bagdex.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bolsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    private Tipo tipo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "bolsa_pokemon_mapping",
            joinColumns = @JoinColumn(name = "bolsa_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<Pokemon> pokemon;

    private Timestamp data;

    @ManyToOne
    private Treinador treinador;
}
