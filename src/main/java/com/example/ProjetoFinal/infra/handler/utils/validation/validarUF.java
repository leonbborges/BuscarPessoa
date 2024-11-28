package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.controller.dto.UFDto;
import com.example.ProjetoFinal.infra.exception.UF.UFNullParamException;

public class validarUF {

    public static void validarEntradaUF(UFDto dto){

        if (dto.getSigla() == null || dto.getSigla().isEmpty()) {
            throw new UFNullParamException(
                    "Não foi possível incluir sigla no banco de dados. Motivo: o campo 'sigla' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (dto.getNome() == null || dto.getNome().isEmpty()) {
            throw new UFNullParamException(
                    "Não foi possível incluir o nome no banco de dados. Motivo: o campo 'nome' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (dto.getStatus() == null) {
            throw new UFNullParamException(
                    "Não foi possível incluir status no banco de dados. Motivo: o status 'status' não pode ser " +
                            "nulo ou vazio."
            );
        }
    }

    public static void validarCogigoUF(UFDto dto){

        if (dto.getCodigoUF() == null) {
            throw new UFNullParamException(
                    "Não foi possível incluir codigo UF no banco de dados. Motivo: o codigo UF não pode ser " +
                            "nulo ou vazio."
            );
        }
    }

}
