package com.example.ProjetoFinal.repository;

import com.example.ProjetoFinal.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT DISTINCT p FROM Pessoa p " +
            "WHERE (:codigoPessoa IS NULL OR p.codigoPessoa = :codigoPessoa) AND " +
            "(:login IS NULL OR LOWER(p.login) = LOWER(:login)) AND " +
            "(:status IS NULL OR p.status = :status)")
    Optional<Pessoa> findByIdWithEnderecos(@Param("codigoPessoa") Long codigoPessoa,
                                           @Param("login") String login,
                                           @Param("status") Integer status);

    @Query("SELECT DISTINCT p FROM Pessoa p " +
            "WHERE (:login IS NULL OR LOWER(p.login) = LOWER(:login)) AND " +
            "(:status IS NULL OR p.status = :status)")
    List<Pessoa> findByLoginAndStatus(@Param("login") String login,
                                      @Param("status") Integer status);

    Optional<Pessoa> findOneByLogin (String login);
}
