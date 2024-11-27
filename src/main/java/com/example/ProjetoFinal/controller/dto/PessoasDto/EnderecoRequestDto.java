package com.example.ProjetoFinal.controller.dto.PessoasDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoRequestDto {

    private Long codigoEndereco;
    private Long codigoPessoa;
    private Long codigoBairro;
    private String nomeRua;
    private String numero;
    private String complemento;
    private String cep;
    private PessoaRequestBairroDto bairro;

    public EnderecoRequestDto(Long codigoEndereco, Long codigoPessoa, Long codigoBairro, String nomeRua, String numero, String complemento, String cep, PessoaRequestBairroDto bairro) {
        this.codigoEndereco = codigoEndereco;
        this.codigoPessoa = codigoPessoa;
        this.codigoBairro = codigoBairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
    }
}
