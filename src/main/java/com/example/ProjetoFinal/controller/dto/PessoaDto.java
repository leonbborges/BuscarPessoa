package com.example.ProjetoFinal.controller.dto;

import com.example.ProjetoFinal.infra.personalitedException.ValidStatus;
import jakarta.validation.constraints.Digits;
import lombok.Data;

import java.util.List;

@Data
public class PessoaDto {
    @Digits(integer = 9, fraction = 0, message = "O campo 'codigoPessoa' deve ser um número inteiro.")
    private Long codigoPessoa;

    private String nome;

    private String sobrenome;

    @Digits(integer = 3, fraction = 0, message = "O campo 'idade' deve ser um número inteiro.")
    private Integer idade;

    private String login;

    private String senha;

    @ValidStatus
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
