package com.example.ProjetoFinal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ENDERECO")
@Data
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
    @SequenceGenerator(name = "seq_endereco", sequenceName = "SEQUENCE_ENDERECO", allocationSize = 1)
    @Column(name = "CODIGO_ENDERECO", nullable = false)
    private Long codigoEndereco;

    @ManyToOne
    @JoinColumn(name = "CODIGO_PESSOA", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "CODIGO_BAIRRO", nullable = false)
    private Bairro bairro;

    @Column(name = "NOME_RUA", nullable = false, length = 256)
    private String nomeRua;

    @Column(name = "NUMERO", nullable = false, length = 10)
    private String numero;

    @Column(name = "COMPLEMENTO", length = 20)
    private String complemento;

    @Column(name = "CEP", nullable = false, length = 10)
    private String cep;

    public Endereco(Long codigoEndereco, Pessoa pessoa, Bairro bairro, String nomeRua, String numero, String complemento, String cep) {
        this.codigoEndereco = codigoEndereco;
        this.pessoa = pessoa;
        this.bairro = bairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }
}