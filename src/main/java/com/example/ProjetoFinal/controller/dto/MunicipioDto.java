package com.example.ProjetoFinal.controller.dto;

import lombok.Data;

@Data
public class MunicipioDto {

    private Long codigoMunicipio;
    private Long codigoUF;
    private String nome;
    private Integer status;

    public MunicipioDto(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.codigoUF = codigoUF;
        this.status = status;
    }
}
