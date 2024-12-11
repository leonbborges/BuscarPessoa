package com.example.ProjetoFinal.repository;

import com.example.ProjetoFinal.entity.Endereco;
import com.example.ProjetoFinal.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByPessoaCodigoPessoa(Long codigoPessoa);

    Optional<Endereco> findByBairroCodigoBairroAndNomeRua(Long codigoBairro, String nome);

    void deleteByPessoaCodigoPessoa(Long codigoPessoa);

    List<Endereco> findByPessoa(Pessoa pessoa);
}
