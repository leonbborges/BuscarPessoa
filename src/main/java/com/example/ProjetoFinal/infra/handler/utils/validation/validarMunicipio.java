package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.controller.dto.MunicipioDto;
import com.example.ProjetoFinal.infra.exception.Municipio.MunicipioNullParamException;

public class validarMunicipio {

    public static void validarEntradaMunicipio(MunicipioDto dto){

        if (dto.getCodigoUF() == null ) {
            throw new MunicipioNullParamException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: o campo codigoUF não pode ser " +
                            "nulo"
            );
        }

        if (dto.getNome() == null || dto.getNome().isEmpty()) {
            throw new MunicipioNullParamException(
                    "Não foi possível incluir o Municipio no banco de dados. Motivo: o campo 'nome' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (dto.getStatus() == null) {
            throw new MunicipioNullParamException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: o status 'status' não pode ser " +
                            "nulo ou vazio."
            );
        }
    }

    public static void validarCogigoMunicipio(MunicipioDto dto){

        if (dto.getCodigoMunicipio() == null) {
            throw new MunicipioNullParamException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: o 'codigo Municipio' " +
                            "não pode ser nulo ou vazio."
            );
        }
    }
}
