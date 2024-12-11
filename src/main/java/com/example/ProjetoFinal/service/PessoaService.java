package com.example.ProjetoFinal.service;

import com.example.ProjetoFinal.controller.dto.PessoaDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.EnderecoRequestDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.PessoaRequestBairroDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.PessoaRequestDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.PessoaRequestMunicipioDto;
import com.example.ProjetoFinal.controller.dto.UFDto;
import com.example.ProjetoFinal.entity.*;
import com.example.ProjetoFinal.infra.exception.Bairro.BairroInsertException;
import com.example.ProjetoFinal.infra.exception.Bairro.NotFoundBairroException;
import com.example.ProjetoFinal.infra.exception.Pessoa.NotFoundPessoaException;
import com.example.ProjetoFinal.infra.exception.Pessoa.PessoaInsertException;
import com.example.ProjetoFinal.infra.handler.utils.validation.validarEndereco;
import com.example.ProjetoFinal.infra.handler.utils.validation.validarPessoa;
import com.example.ProjetoFinal.repository.BairroRepository;
import com.example.ProjetoFinal.repository.EnderecoRepository;
import com.example.ProjetoFinal.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        List<Endereco> enderecosPessoa = enderecoRepository.findByPessoaCodigoPessoa(codigoPessoa);

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

        validarPessoa.validarEntradaPessoa(pessoaDto);

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

        if (pessoaDto.getEnderecos() == null || pessoaDto.getEnderecos().isEmpty()) {
            throw new PessoaInsertException("É necessário informar pelo menos um endereço para a Pessoa.");
        }

        Set<String> enderecoKeys = new HashSet<>();
        boolean hasDuplicates = pessoaDto.getEnderecos().stream().anyMatch(enderecoRequest -> {
            String key = enderecoRequest.getCodigoBairro() + "|" + enderecoRequest.getNomeRua() + "|" + enderecoRequest.getNumero();
            return !enderecoKeys.add(key); // Retorna true se já existe no conjunto
        });

        if (hasDuplicates) {
            throw new PessoaInsertException("Não é possível incluir endereços duplicados na mesma pessoa.");
        }

        // Criar e salvar endereços relacionados
        List<Endereco> enderecos = pessoaDto.getEnderecos().stream().map(enderecoRequest -> {

            validarEndereco.validarEntradaEndereco(enderecoRequest);

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

        validarPessoa.validarEntradaPessoa(pessoaDto);
        validarPessoa.validarCodigoPessoa(pessoaDto);

        // Buscar pessoa no banco de dados
        Pessoa pessoa = pessoaRepository.findById(pessoaDto.getCodigoPessoa())
                .orElseThrow(() -> new NotFoundPessoaException("Não foi possível encontrar uma Pessoa com o código disponibilizado"));

        pessoa.setNome(pessoaDto.getNome());
        pessoa.setSobrenome(pessoaDto.getSobrenome());
        pessoa.setIdade(pessoaDto.getIdade());
        pessoa.setLogin(pessoaDto.getLogin());
        pessoa.setSenha(pessoaDto.getSenha());
        pessoa.setStatus(pessoaDto.getStatus());

        Optional<Pessoa> existingPessoa = pessoaRepository.findByLogin(pessoa.getLogin());
        if (existingPessoa.isPresent() && !existingPessoa.get().getCodigoPessoa().equals(pessoa.getCodigoPessoa())) {
            throw new PessoaInsertException(
                    "Não foi possível incluir Pessoa no banco de dados. Motivo: já existe um registro de Pessoa " +
                            "com o Login " + pessoa.getLogin() + " cadastrado(a) no banco de dados."
            );
        }

        if (pessoaDto.getEnderecos() == null || pessoaDto.getEnderecos().isEmpty()) {
            // Caso queira lançar uma exceção
            throw new PessoaInsertException("É necessário informar pelo menos um endereço para a Pessoa.");
        }

        Set<String> enderecoKeys = new HashSet<>();
        boolean hasDuplicates = pessoaDto.getEnderecos().stream().anyMatch(enderecoRequest -> {
            String key = enderecoRequest.getCodigoBairro() + "|" + enderecoRequest.getNomeRua()
                    + "|" + enderecoRequest.getNumero();
            return !enderecoKeys.add(key);
        });

        if (hasDuplicates) {
            throw new PessoaInsertException("Não é possível incluir endereços duplicados na mesma pessoa.");
        }



        List<Endereco> enderecosAtuais = enderecoRepository.findByPessoa(pessoa);

        List<Endereco> enderecosParaExcluir = enderecosAtuais.stream()
                .filter(enderecoAtual -> pessoaDto.getEnderecos().stream()
                        .noneMatch(enderecoDto -> enderecoDto.getCodigoEndereco() != null &&
                                enderecoDto.getCodigoEndereco().equals(enderecoAtual.getCodigoEndereco())))
                .toList();

        // Filtrar e atualizar endereços existentes no DTO e no banco
        List<Endereco> enderecosParaAtualizar = pessoaDto.getEnderecos().stream()
                .filter(enderecoDto -> enderecoDto.getCodigoEndereco() != null)
                .map(enderecoDto -> {
                    Endereco enderecoAtual = enderecosAtuais.stream()
                            .filter(e -> e.getCodigoEndereco().equals(enderecoDto.getCodigoEndereco()))
                            .findFirst()
                            .orElseThrow(() -> new PessoaInsertException("Endereço não encontrado para atualização"));

                    validarEndereco.validarEntradaEndereco(enderecoDto);
                    enderecoAtual.setNomeRua(enderecoDto.getNomeRua());
                    enderecoAtual.setNumero(enderecoDto.getNumero());
                    enderecoAtual.setComplemento(enderecoDto.getComplemento());
                    enderecoAtual.setCep(enderecoDto.getCep());
                    enderecoAtual.setBairro(bairroRepository.findById(enderecoDto.getCodigoBairro())
                            .orElseThrow(() -> new BairroInsertException("Bairro não encontrado")));

                    return enderecoAtual;
                }).toList();

        List<Endereco> novosEnderecos = pessoaDto.getEnderecos().stream()
                .filter(enderecoDto -> enderecoDto.getCodigoEndereco() == null) // Novo endereço
                .map(enderecoDto -> {
                    validarEndereco.validarEntradaEndereco(enderecoDto);

                    Endereco novoEndereco = new Endereco();
                    novoEndereco.setPessoa(pessoa);
                    novoEndereco.setNomeRua(enderecoDto.getNomeRua());
                    novoEndereco.setNumero(enderecoDto.getNumero());
                    novoEndereco.setComplemento(enderecoDto.getComplemento());
                    novoEndereco.setCep(enderecoDto.getCep());
                    novoEndereco.setBairro(bairroRepository.findById(enderecoDto.getCodigoBairro())
                            .orElseThrow(() -> new BairroInsertException("Bairro não encontrado")));

                    return novoEndereco;
                }).toList();

        enderecoRepository.deleteAll(enderecosParaExcluir);


        pessoaRepository.save(pessoa);
        enderecoRepository.saveAll(enderecosParaAtualizar);
        enderecoRepository.saveAll(novosEnderecos);

        return pessoaRepository.findAll();
    }

}
