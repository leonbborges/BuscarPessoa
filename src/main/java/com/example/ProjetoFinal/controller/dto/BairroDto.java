package com.example.ProjetoFinal.controller.dto;

import lombok.Data;

@Data
public class BairroDto {


    private Long codigoBairro;
    private Long codigoMunicipio;
    private String nome;
    private Integer status;

    public BairroDto(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }
}
