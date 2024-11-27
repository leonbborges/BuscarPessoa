package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.entity.UF;
import com.example.ProjetoFinal.infra.exception.UF.UFNullParamException;

public class validarUF {

    public static void validarEntradaUF(UF uf){

        if (uf.getSigla() == null || uf.getSigla().isEmpty()) {
            throw new UFNullParamException(
                    "Não foi possível incluir sigla no banco de dados. Motivo: o campo 'sigla' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (uf.getNome() == null || uf.getNome().isEmpty()) {
            throw new UFNullParamException(
                    "Não foi possível incluir o nome no banco de dados. Motivo: o campo 'nome' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (uf.getStatus() == null) {
            throw new UFNullParamException(
                    "Não foi possível incluir status no banco de dados. Motivo: o status 'status' não pode ser " +
                            "nulo ou vazio."
            );
        }
    }

    public static void validarCogigoUF(UF uf){

        if (uf.getCodigoUF() == null) {
            throw new UFNullParamException(
                    "Não foi possível incluir codigo UF no banco de dados. Motivo: o codigo UF não pode ser " +
                            "nulo ou vazio."
            );
        }
    }

}
