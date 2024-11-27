package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.entity.Municipio;
import com.example.ProjetoFinal.infra.exception.Municipio.MunicipioNullParamException;
import com.example.ProjetoFinal.infra.exception.UF.UFNullParamException;

public class validarMunicipio {

    public static void validarEntradaMunicipio(Municipio municipio){

        if (municipio.getUf().getCodigoUF() == null) {
            throw new MunicipioNullParamException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: o campo codigoUF não pode ser " +
                            "nulo"
            );
        }

        if (municipio.getNome() == null || municipio.getNome().isEmpty()) {
            throw new MunicipioNullParamException(
                    "Não foi possível incluir o Municipio no banco de dados. Motivo: o campo 'nome' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (municipio.getStatus() == null) {
            throw new MunicipioNullParamException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: o status 'status' não pode ser " +
                            "nulo ou vazio."
            );
        }
    }

    public static void validarCogigoMunicipio(Municipio municipio){

        if (municipio.getCodigoMunicipio() == null) {
            throw new MunicipioNullParamException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: o 'codigo Municipio' " +
                            "não pode ser nulo ou vazio."
            );
        }
    }
}
