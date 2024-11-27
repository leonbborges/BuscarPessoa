package com.example.ProjetoFinal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_BAIRRO")
@Data
@NoArgsConstructor
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bairro")
    @SequenceGenerator(name = "seq_bairro", sequenceName = "SEQUENCE_BAIRRO", allocationSize = 1)
    @Column(name = "CODIGO_BAIRRO", nullable = false)
    private Long codigoBairro;

    @ManyToOne
    @JoinColumn(name = "CODIGO_MUNICIPIO", nullable = false)
    private Municipio municipio;

    @Column(name = "NOME", nullable = false, length = 256)
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public Bairro(Municipio municipio, String nome, Integer status) {
        this.municipio = municipio;
        this.nome = nome;
        this.status = status;
    }

    public Bairro(Long codigoBairro, Municipio municipio, String nome, Integer status) {
        this.codigoBairro = codigoBairro;
        this.municipio = municipio;
        this.nome = nome;
        this.status = status;
    }
}
