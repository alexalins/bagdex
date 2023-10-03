package com.alexalins.bagdex.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
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

    private Tipo tipo;

    @ManyToMany
    @JoinTable(
            name = "bolsa_pokemon_mapping",
            joinColumns = @JoinColumn(name = "bolsa_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<Pokemon> pokemon;

    private Date data;

    @ManyToOne
    private Treinador treinador;
}
