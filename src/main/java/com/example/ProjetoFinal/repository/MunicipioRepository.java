package com.example.ProjetoFinal.repository;

import com.example.ProjetoFinal.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    @Query("SELECT m FROM Municipio m WHERE " +
            "(:codigoMunicipio IS NULL OR m.codigoMunicipio = :codigoMunicipio) AND " +
            "(:ufCodigo IS NULL OR m.uf.codigoUF = :ufCodigo) AND " +
            "(:nome IS NULL OR LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:status IS NULL OR m.status = :status)")
    Optional<Municipio> findOneByCriteria(@Param("codigoMunicipio") Long codigoMunicipio,
                                          @Param("ufCodigo") Long ufCodigo,
                                          @Param("nome") String nome,
                                          @Param("status") Integer status);

    @Query("SELECT m FROM Municipio m WHERE " +
            "(:codigoUF IS NULL OR m.uf.codigoUF = :codigoUF) AND " +
            "(:nome IS NULL OR LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:status IS NULL OR m.status = :status)")
    List<Municipio> searchByParameters(
            @Param("codigoUF") Long codigoUF,
            @Param("nome") String nome,
            @Param("status") Integer status);


    Optional<Municipio> findOneByNome(String nome);


}
