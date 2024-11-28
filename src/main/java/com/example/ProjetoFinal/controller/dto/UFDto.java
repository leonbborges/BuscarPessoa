package com.example.ProjetoFinal.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UFDto {
    private Long codigoUF;
    private String sigla;
    private String nome;

    @Min(value = 0, message = "O status deve ser no mínimo 0.")
    @Max(value = 1, message = "O status deve ser no máximo 1.")
    private Integer status;

    public UFDto(Long codigoUF, String sigla, String nome, Integer status) {
        this.codigoUF = codigoUF;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }
}
