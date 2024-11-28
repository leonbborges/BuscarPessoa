package com.example.ProjetoFinal.controller.dto;

import com.example.ProjetoFinal.infra.personalitedException.ValidNumeric;
import jakarta.validation.constraints.Digits;
import lombok.Data;

import java.util.List;

@Data
public class PessoaDto {

    private Long codigoPessoa;

    private String nome;

    private String sobrenome;

    private Integer idade;

    private String login;

    private String senha;

    private Integer status;

    private List<EnderecoDto> enderecos;

    public PessoaDto(Long codigoPessoa, String nome, String sobrenome, Integer idade, String login, String senha, Integer status, List<EnderecoDto> enderecos) {
        this.codigoPessoa = codigoPessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.enderecos = enderecos;
    }
}
