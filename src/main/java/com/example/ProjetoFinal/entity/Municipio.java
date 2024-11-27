package com.example.ProjetoFinal.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_MUNICIPIO")
@Data
@NoArgsConstructor
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_municipio")
    @SequenceGenerator(name = "seq_municipio", sequenceName = "SEQUENCE_MUNICIPIO", allocationSize = 1)
    @Column(name = "CODIGO_MUNICIPIO", nullable = false)
    private Long codigoMunicipio;

    @ManyToOne
    @JoinColumn(name = "CODIGO_UF", nullable = false)
    private UF uf;

    @Column(name = "NOME", length = 256)
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public Municipio(UF uf, String nome, Integer status) {
        this.uf = uf;
        this.nome = nome;
        this.status = status;
    }

    public Municipio(Long codigoMunicipio, UF uf, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.uf = uf;
        this.nome = nome;
        this.status = status;
    }
}
