package com.example.ProjetoFinal.controller;

import com.example.ProjetoFinal.controller.dto.PessoaDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.EnderecoRequestDto;
import com.example.ProjetoFinal.controller.dto.PessoasDto.PessoaRequestDto;
import com.example.ProjetoFinal.entity.Pessoa;
import com.example.ProjetoFinal.infra.personalitedException.ValidNumeric;
import com.example.ProjetoFinal.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<?> listarMunicipiosFiltrados(
            @Valid @ValidNumeric @RequestParam(required = false) Long codigoPessoa,
            @RequestParam(required = false) String login,
            @Valid @ValidNumeric @RequestParam(required = false) Integer status){

        List<Pessoa> pessoas =  new ArrayList<>();
        List<EnderecoRequestDto> enderecos = new ArrayList<>();

        if(codigoPessoa!= null){
            PessoaRequestDto pessoaDto = pessoaService.getPessoaComEnderecos(codigoPessoa, login, status);

            return ResponseEntity.ok(pessoaDto);
        }
        else if(login != null || status != null){
            pessoas = pessoaService.buscarPessoaorAtributos(login, status);
        }
        else{
            pessoas = pessoaService.buscarTodasAsPessoas();
        }

        List<PessoaRequestDto> pessoaDtos = pessoas.stream().map(pessoa -> {
            PessoaRequestDto pessoaDto  = new PessoaRequestDto(pessoa.getCodigoPessoa(),
                    pessoa.getNome(),
                    pessoa.getSobrenome(),
                    pessoa.getIdade(),
                    pessoa.getLogin(),
                    pessoa.getSenha(),
                    pessoa.getStatus(),
                    enderecos
                    );
            return pessoaDto;
        }).toList();

        return ResponseEntity.ok(pessoaDtos);

    }

    @PostMapping
    public ResponseEntity<List<PessoaRequestDto>> salvarPessoa(@Valid @RequestBody PessoaDto dto) {

        List<Pessoa> pessoas = pessoaService.salvarPessoa(dto);
        List<EnderecoRequestDto> enderecos = new ArrayList<>();

        List<PessoaRequestDto> pessoaDtos = pessoas.stream().map(pessoa -> {
            PessoaRequestDto pessoaDto  = new PessoaRequestDto(pessoa.getCodigoPessoa(),
                    pessoa.getNome(),
                    pessoa.getSobrenome(),
                    pessoa.getIdade(),
                    pessoa.getLogin(),
                    pessoa.getSenha(),
                    pessoa.getStatus(),
                    enderecos
            );
            return pessoaDto;
        }).toList();

        return ResponseEntity.ok(pessoaDtos);
    }

    @PutMapping
    public ResponseEntity<List<PessoaRequestDto>> attualizarPessoa(@RequestBody PessoaDto dto) {

        List<Pessoa> pessoas = pessoaService.atualizarPessoa(dto);
        List<EnderecoRequestDto> enderecos = new ArrayList<>();

        List<PessoaRequestDto> pessoaDtos = pessoas.stream().map(pessoa -> {
            PessoaRequestDto pessoaDto  = new PessoaRequestDto(pessoa.getCodigoPessoa(),
                    pessoa.getNome(),
                    pessoa.getSobrenome(),
                    pessoa.getIdade(),
                    pessoa.getLogin(),
                    pessoa.getSenha(),
                    pessoa.getStatus(),
                    enderecos
            );
            return pessoaDto;
        }).toList();

        return ResponseEntity.ok(pessoaDtos);
    }
}
