package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.entity.Bairro;
import com.example.ProjetoFinal.infra.exception.Bairro.BairroNullParamException;
import com.example.ProjetoFinal.infra.exception.UF.UFNullParamException;

public class validarBairro {
    public static void validarEntradaBairro(Bairro bairro){

        if (bairro.getMunicipio().getCodigoMunicipio() == null) {
            throw new BairroNullParamException(
                    "Não foi possível incluir Bairro no banco de dados. Motivo: o campo codigoUF não pode ser " +
                            "nulo"
            );
        }

        if (bairro.getNome() == null || bairro.getNome().isEmpty()) {
            throw new BairroNullParamException(
                    "Não foi possível incluir o Municipio no banco de dados. Motivo: o campo 'nome' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (bairro.getStatus() == null) {
            throw new BairroNullParamException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: o status 'status' não pode ser " +
                            "nulo ou vazio."
            );
        }
    }

    public static void validarCogigoMunicipio(Bairro bairro){

        if (bairro.getCodigoBairro() == null) {
            throw new BairroNullParamException(
                    "Não foi possível incluir Bairro no banco de dados. Motivo: o 'codigo do Bairro' " +
                            "não pode ser nulo ou vazio."
            );
        }
    }
}
