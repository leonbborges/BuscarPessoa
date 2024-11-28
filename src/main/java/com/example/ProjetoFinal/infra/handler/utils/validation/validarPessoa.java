package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.controller.dto.PessoaDto;
import com.example.ProjetoFinal.infra.exception.Pessoa.PessoaNullParamException;

public class validarPessoa {

    public static void validarEntradaPessoa(PessoaDto dto) {

        if (dto.getNome() == null || dto.getNome().isEmpty()) {
            throw new PessoaNullParamException(
                    "Não foi possível incluir a Pessoa no banco de dados. Motivo: o campo 'nome' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (dto.getSobrenome() == null || dto.getSobrenome().isEmpty()) {
            throw new PessoaNullParamException(
                    "Não foi possível incluir o Pessoa no banco de dados. Motivo: o campo 'sobrenome' " +
                            "não pode ser nulo ou vazio."
            );
        }

        if (dto.getIdade() == null ) {
            throw new PessoaNullParamException(
                    "Não foi possível incluir o Pessoa no banco de dados. Motivo: o campo 'idade' não pode ser " +
                            "nulo ou vazio."
            );
        }

        if (dto.getLogin() == null || dto.getLogin().isEmpty()) {
            throw new PessoaNullParamException(
                    "Não foi possível incluir o Pessoa no banco de dados. Motivo: o campo 'login' " +
                            "não pode ser nulo ou vazio."
            );
        }

        if (dto.getSenha() == null || dto.getSenha().isEmpty()) {
            throw new PessoaNullParamException(
                    "Não foi possível incluir o Pessoa no banco de dados. Motivo: o campo 'senha' " +
                            "não pode ser nulo ou vazio."
            );
        }

        if (dto.getStatus() == null) {
            throw new PessoaNullParamException(
                    "Não foi possível incluir o Pessoa no banco de dados. Motivo: o campo 'status' " +
                            "não pode ser nulo ou vazio."
            );
        }
    }


    public static void validarCodigoPessoa(PessoaDto dto) {

        if (dto.getCodigoPessoa() == null) {
            throw new PessoaNullParamException(
                    "Não foi possível incluir a Pessoa no banco de dados. Motivo: o campo 'codigo pessoa' " +
                            "não pode ser nulo ou vazio."
            );
        }
    }
}
