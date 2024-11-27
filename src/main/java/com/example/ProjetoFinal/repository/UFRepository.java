package com.example.ProjetoFinal.repository;

import com.example.ProjetoFinal.entity.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UFRepository extends JpaRepository<UF, Long> {


    Optional<UF> findBySigla(String sigla);

    Optional<UF> findByNome(String nome);

    List<UF> findByStatus(Integer status);


    @Query("SELECT uf FROM UF uf WHERE " +
            "(:sigla IS NULL OR LOWER(uf.sigla) = LOWER(:sigla)) AND " +
            "(:nome IS NULL OR LOWER(uf.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:codigoUF IS NULL OR uf.codigoUF = :codigoUF) AND " +
            "(:status IS NULL OR uf.status = :status)")
    Optional<UF> findOneByCriteria(@Param("sigla") String sigla,
                                   @Param("nome") String nome,
                                   @Param("codigoUF") Long codigoUF,
                                   @Param("status") Integer status);
}
