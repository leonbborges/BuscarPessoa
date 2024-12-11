package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.controller.dto.EnderecoDto;
import com.example.ProjetoFinal.infra.exception.Endereco.EnderecoNullParamException;

public class validarEndereco {

    public static void validarEntradaEndereco(EnderecoDto dto) {
        if (dto.getNomeRua() == null || dto.getNomeRua().isEmpty()) {
            throw new EnderecoNullParamException(
                    "Não foi possível incluir o Endereco no banco de dados. Motivo: o campo 'nome da rua' não " +
                            "pode ser nulo ou vazio."
            );
        }

        if (dto.getNumero() == null || dto.getNumero().isEmpty()) {
            throw new EnderecoNullParamException(
                    "Não foi possível incluir o Endereco no banco de dados. Motivo: o campo 'Número não " +
                            "pode ser nulo ou vazio."
            );
        }

        if (dto.getComplemento() == null || dto.getComplemento().isEmpty()) {
            throw new EnderecoNullParamException(
                    "Não foi possível incluir o Endereco no banco de dados. Motivo: o campo 'complemento não " +
                            "pode ser nulo ou vazio."
            );
        }

        if (dto.getCep() == null || dto.getCep().isEmpty()) {
            throw new EnderecoNullParamException(
                    "Não foi possível incluir o Endereco no banco de dados. Motivo: o campo 'cep não " +
                            "pode ser nulo ou vazio."
            );
        }

        if (dto.getCodigoBairro() == null ) {
            throw new EnderecoNullParamException(
                    "Não foi possível incluir o Endereco no banco de dados. Motivo: o campo 'codigo bairro não " +
                            "pode ser nulo ou vazio."
            );
        }

    }
}
