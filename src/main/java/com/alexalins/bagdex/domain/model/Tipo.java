package com.alexalins.bagdex.domain.model;

import lombok.Getter;

@Getter
public enum Tipo {
    NENHUM(1, "Nenhum"),
    QUERO_TER(2, "Quero ter"),
    JA_TENHO(3, "Já tenho"),
    ESTOU_PROCURANDO(4, "Estou procurando");


    private final int codigo;
    private final String nome;

    private Tipo(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
}
