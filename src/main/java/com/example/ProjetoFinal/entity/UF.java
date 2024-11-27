package com.example.ProjetoFinal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TB_UF")
@Data
@NoArgsConstructor
public class UF {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_uf")
    @SequenceGenerator(name = "seq_uf", sequenceName = "SEQUENCE_UF", allocationSize = 1)
    @Column(name = "CODIGO_UF", nullable = false)
    private Long codigoUF;

    @Column(name = "SIGLA", nullable = false, length = 3)
    private String sigla;

    @Column(name = "NOME", nullable = false, length = 60)
    private String nome;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    public UF(String sigla, String nome, Integer status) {
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }

    public UF(Long codigoUF, String sigla, String nome, Integer status) {
        this.codigoUF = codigoUF;
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }
}
