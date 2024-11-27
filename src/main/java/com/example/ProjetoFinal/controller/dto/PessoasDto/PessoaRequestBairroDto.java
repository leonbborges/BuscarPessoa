package com.example.ProjetoFinal.controller.dto.PessoasDto;

import lombok.Data;

@Data
public class PessoaRequestBairroDto {

    private Long codigoBairro;
    private Long codigoMunicipio;
    private String nome;
    private Integer status;
    private PessoaRequestMunicipioDto municipioDto;

    public PessoaRequestBairroDto(Long codigoBairro, Long codigoMunicipio, String nome, Integer status, PessoaRequestMunicipioDto municipioDto) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
        this.municipioDto = municipioDto;
    }
}
