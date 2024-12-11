package com.example.ProjetoFinal.controller.dto;

import lombok.Data;

@Data
public class UFDto {

    private Long codigoUF;
    private String sigla;
    private String nome;
    private Integer status;

    public UFDto(Long codigoUF, String sigla, String nome, Integer status) {
        this.codigoUF = codigoUF;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }
}
