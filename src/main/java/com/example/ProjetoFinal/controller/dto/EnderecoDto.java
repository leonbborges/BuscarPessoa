package com.example.ProjetoFinal.controller.dto;


import com.example.ProjetoFinal.controller.dto.PessoasDto.PessoaRequestBairroDto;
import lombok.Data;

@Data
public class EnderecoDto {

    private Long codigoEndereco;
    private String nomeRua;
    private String numero;
    private String complemento;
    private String cep;
    private Long codigoBairro;

    public EnderecoDto(String nomeRua, String numero, String complemento, String cep, Long codigoBairro) {
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.codigoBairro = codigoBairro;
    }
}
