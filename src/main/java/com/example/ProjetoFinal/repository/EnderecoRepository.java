package com.example.ProjetoFinal.repository;

import com.example.ProjetoFinal.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByPessoaCodigoPessoa(Long codigoPessoa);

    @Query("SELECT e FROM Endereco e " +
            "WHERE e.bairro.codigoBairro = :codigoBairro " +
            "AND e.nomeRua = :nomeRua " +
            "AND e.numero = :numero")
    Optional<Endereco> findByBairroCodigoBairroNomeRuaNumero(
            @Param("codigoBairro") Long codigoBairro,
            @Param("nomeRua") String nomeRua,
            @Param("numero") String numero);
}
