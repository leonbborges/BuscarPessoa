package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.controller.dto.BairroDto;
import com.example.ProjetoFinal.infra.exception.Bairro.BairroNullParamException;

public class validarBairro {
    public static void validarEntradaBairro(BairroDto dto){

        if (dto.getCodigoMunicipio() == null) {
            throw new BairroNullParamException(
                    "Não foi possível incluir Bairro no banco de dados. Motivo: o campo getCodigo Municipio " +
                            "não pode ser nulo"
            );
        }

        if (dto.getNome() == null || dto.getNome().isEmpty()) {
            throw new BairroNullParamException(
                    "Não foi possível incluir o Municipio no banco de dados. Motivo: o campo 'nome' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (dto.getStatus() == null) {
            throw new BairroNullParamException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: o status 'status' não pode ser " +
                            "nulo ou vazio."
            );
        }
    }

    public static void validarCogigoMunicipio(BairroDto dto){
        if (dto.getCodigoBairro() == null) {
            throw new BairroNullParamException(
                    "Não foi possível incluir Bairro no banco de dados. Motivo: o 'codigo do Bairro' " +
                            "não pode ser nulo ou vazio."
            );
        }
    }
}
