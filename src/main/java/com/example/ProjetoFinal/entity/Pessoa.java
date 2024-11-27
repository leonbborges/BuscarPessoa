package com.example.ProjetoFinal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_PESSOA")
@Data
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "SEQUENCE_PESSOA", allocationSize = 1)
    @Column(name = "CODIGO_PESSOA", nullable = false)
    private Long codigoPessoa;

    @Column(name = "NOME", nullable = false, length = 256)
    private String nome;

    @Column(name = "SOBRENOME", nullable = false, length = 256)
    private String sobrenome;

    @Column(name = "IDADE", nullable = false)
    private Integer idade;

    @Column(name = "LOGIN", nullable = false, length = 50)
    private String login;

    @Column(name = "SENHA", nullable = false, length = 50)
    private String senha;

    @Column(name = "STATUS", nullable = false)
    private Integer status;
}
