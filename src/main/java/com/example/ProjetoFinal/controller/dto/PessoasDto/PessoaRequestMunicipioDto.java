package com.example.ProjetoFinal.controller.dto.PessoasDto;

import com.example.ProjetoFinal.controller.dto.UFDto;
import lombok.Data;

@Data
public class PessoaRequestMunicipioDto {

    private Long codigoMunicipio;
    private Long codigoUF;
    private String nome;
    private Integer status;
    private UFDto ufDto;

    public PessoaRequestMunicipioDto(Long codigoMunicipio, Long codigoUF, String nome, Integer status, UFDto ufDto) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
        this.ufDto = ufDto;
    }
}
