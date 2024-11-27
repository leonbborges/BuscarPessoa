package com.example.ProjetoFinal.repository;

import com.example.ProjetoFinal.entity.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {

    @Query("SELECT b FROM Bairro b WHERE " +
            "(:codigoBairro IS NULL OR b.codigoBairro = :codigoBairro) AND " +
            "(:codigoMunicipio IS NULL OR b.municipio.codigoMunicipio = :codigoMunicipio) AND " +
            "(:nome IS NULL OR LOWER(b.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:status IS NULL OR b.status = :status)")
    Optional<Bairro> findOneByCriteria(@Param("codigoBairro") Long codigoBairro,
                                       @Param("codigoMunicipio") Long codigoMunicipio,
                                       @Param("nome") String nome,
                                       @Param("status") Integer status);

    @Query("SELECT b FROM Bairro b WHERE " +
            "(:codigoMunicipio IS NULL OR b.municipio.codigoMunicipio = :codigoMunicipio) AND " +
            "(:nome IS NULL OR LOWER(b.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:status IS NULL OR b.status = :status)")
    List<Bairro> searchByParameters(@Param("codigoMunicipio") Long codigoMunicipio,
                                    @Param("nome") String nome,
                                    @Param("status") Integer status);

    Optional<Bairro> findByMunicipioCodigoMunicipioAndNome(Long codigoBairro, String nome);
}
