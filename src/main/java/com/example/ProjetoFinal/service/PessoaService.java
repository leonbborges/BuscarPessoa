package com.example.ProjetoFinal.service;

import com.example.ProjetoFinal.controller.dto.PessoaDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.EnderecoRequestDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.PessoaRequestDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.PessoaRequestBairroDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.PessoaRequestMunicipioDto;
import com.example.ProjetoFinal.controller.dto.UFDto;
import com.example.ProjetoFinal.entity.*;
import com.example.ProjetoFinal.infra.exception.Bairro.NotFoundBairroException;
import com.example.ProjetoFinal.infra.exception.Pessoa.NotFoundPessoaException;
import com.example.ProjetoFinal.infra.exception.Pessoa.PessoaInsertException;
import com.example.ProjetoFinal.repository.BairroRepository;
import com.example.ProjetoFinal.repository.EnderecoRepository;
import com.example.ProjetoFinal.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    BairroRepository bairroRepository;

    public PessoaRequestDto getPessoaComEnderecos(Long codigoPessoa, String login, Integer status) {

        Pessoa pessoa = pessoaRepository.findByIdWithEnderecos(codigoPessoa, login, status)
                .orElseThrow(
                        () -> new NotFoundPessoaException("[]"));

        // Buscar os endereços relacionados à pessoa
        List<Endereco> enderecosPessoa = enderecoRepository.findByPessoaCodigoPessoa(codigoPessoa);

        // Converter os endereços para DTOs
        List<EnderecoRequestDto> enderecos = enderecosPessoa.stream().map(endereco -> {
            Bairro bairro = endereco.getBairro();
            Municipio municipio = bairro.getMunicipio();
            UF uf = municipio.getUf();

            return new EnderecoRequestDto(endereco.getCodigoEndereco(),
                    endereco.getPessoa().getCodigoPessoa(),
                    endereco.getBairro().getCodigoBairro(),
                    endereco.getNomeRua(),
                    endereco.getNumero(),
                    endereco.getComplemento(),
                    endereco.getCep(),
                    new PessoaRequestBairroDto(bairro.getCodigoBairro(),
                            bairro.getMunicipio().getCodigoMunicipio(),
                            bairro.getNome(),
                            bairro.getStatus(),
                            new PessoaRequestMunicipioDto(municipio.getCodigoMunicipio(),
                                    municipio.getUf().getCodigoUF(),
                                    municipio.getNome(),
                                    municipio.getStatus(),
                                    new UFDto(uf.getCodigoUF(),
                                            uf.getSigla(),
                                            uf.getNome(),
                                            uf.getStatus())
                            )
                    )
            );

        }).toList();

        // Retornar a resposta com os dados da pessoa e endereços
        return new PessoaRequestDto(pessoa.getCodigoPessoa(),
                pessoa.getNome(),
                pessoa.getSobrenome(),
                pessoa.getIdade(),
                pessoa.getLogin(),
                pessoa.getSenha(),
                pessoa.getStatus(),
                enderecos);
    }

    public List<Pessoa> buscarTodasAsPessoas(){
        return pessoaRepository.findAll();
    }

    public List<Pessoa> buscarPessoaorAtributos( String login, Integer status) {
        return pessoaRepository.findByLoginAndStatus(login, status);
    }

    public List<Pessoa> salvarPessoa(PessoaDto pessoaDto) {
        // Criar e salvar pessoa
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setSobrenome(pessoaDto.getSobrenome());
        pessoa.setIdade(pessoaDto.getIdade());
        pessoa.setLogin(pessoaDto.getLogin());
        pessoa.setSenha(pessoaDto.getSenha());
        pessoa.setStatus(pessoaDto.getStatus());

        pessoaRepository.findOneByLogin(pessoa.getLogin()).ifPresent(existingPessoa -> {
                throw new PessoaInsertException(
                        "Não foi possível incluir Pessoa no banco de dados. Motivo: já existe um registro de Pessoa " +
                                "com o Login " + pessoa.getLogin() + " cadastrado(a) no banco de dados."
                );
        });

        // Criar e salvar endereços relacionados
        List<Endereco> enderecos = pessoaDto.getEnderecos().stream().map(enderecoRequest -> {
            Endereco endereco = new Endereco();
            endereco.setPessoa(pessoa);
            endereco.setNomeRua(enderecoRequest.getNomeRua());
            endereco.setNumero(enderecoRequest.getNumero());
            endereco.setComplemento(enderecoRequest.getComplemento());
            endereco.setCep(enderecoRequest.getCep());
            endereco.setBairro(bairroRepository.findById(enderecoRequest.getCodigoBairro())
                    .orElseThrow(() -> new NotFoundBairroException("Bairro não encontrado")));
            return endereco;
        }).toList();

        pessoaRepository.save(pessoa);
        enderecoRepository.saveAll(enderecos);

        return pessoaRepository.findAll();
    }

    public List<Pessoa> atualizarPessoa(PessoaDto pessoaDto) {
        // Criar e salvar pessoa
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigoPessoa(pessoaDto.getCodigoPessoa());
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setSobrenome(pessoaDto.getSobrenome());
        pessoa.setIdade(pessoaDto.getIdade());
        pessoa.setLogin(pessoaDto.getLogin());
        pessoa.setSenha(pessoaDto.getSenha());
        pessoa.setStatus(pessoaDto.getStatus());

        pessoaRepository.findOneByLogin(pessoa.getLogin()).ifPresent(existingPessoa -> {
            if (!existingPessoa.getCodigoPessoa().equals(pessoa.getCodigoPessoa())) {
                throw new PessoaInsertException(
                        "Não foi possível incluir Pessoa no banco de dados. Motivo: já existe um registro de Pessoa " +
                                "com o Login " + pessoa.getLogin() + " cadastrado(a) no banco de dados."
                );
            }
        });



        // Criar e salvar endereços relacionados
        List<Endereco> enderecos = pessoaDto.getEnderecos().stream().map(enderecoRequest -> {
            Endereco endereco = new Endereco();
            endereco.setCodigoEndereco(enderecoRequest.getCodigoEndereco());
            endereco.setPessoa(pessoa);
            endereco.setNomeRua(enderecoRequest.getNomeRua());
            endereco.setNumero(enderecoRequest.getNumero());
            endereco.setComplemento(enderecoRequest.getComplemento());
            endereco.setCep(enderecoRequest.getCep());
            endereco.setBairro(bairroRepository.findById(enderecoRequest.getCodigoBairro())
                    .orElseThrow(() -> new NotFoundBairroException("Bairro não encontrado")));
            enderecoRepository.findByBairroCodigoBairroNomeRuaNumero(enderecoRequest.getCodigoBairro(),
                    enderecoRequest.getNomeRua(), enderecoRequest.getNumero()).ifPresent(existingEndereco -> {
                if (!existingEndereco.getCodigoEndereco().equals(endereco.getCodigoEndereco())) {
                    throw new PessoaInsertException(
                            "Não foi possível incluir Endereco no banco de dados. Motivo: já existe um registro de Pessoa " +
                                    "com o Endereco " + endereco.getNomeRua() + " cadastrado(a) no banco de dados."
                    );
                }
            });

            return endereco;
        }).toList();

        pessoaRepository.save(pessoa);
        enderecoRepository.saveAll(enderecos);

        return pessoaRepository.findAll();
    }


}
