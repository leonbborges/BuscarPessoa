package com.example.ProjetoFinal.controller;

import com.example.ProjetoFinal.controller.dto.UFDto;
import com.example.ProjetoFinal.entity.UF;
import com.example.ProjetoFinal.infra.personalitedException.ValidNumeric;
import com.example.ProjetoFinal.service.UFService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/uf")
public class UFController {

    @Autowired
    private UFService ufService;

    @PostMapping
    public ResponseEntity<List<UFDto>> salvarUF(@RequestBody UFDto ufDto) {

        List<UF> ufs = ufService.salvarUF(ufDto);

        List<UFDto> ufDtos = ufs.stream().map(uf -> {
            UFDto ufsDto = new UFDto(uf.getCodigoUF(),
                    uf.getSigla(),
                    uf.getNome(),
                    uf.getStatus());
            return ufsDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(ufDtos);
    }

    @GetMapping
    public ResponseEntity<?> listarUFsFiltrados(
            @RequestParam(required = false) String sigla,
            @RequestParam(required = false) String nome,
            @Valid @ValidNumeric @RequestParam(required = false) Long codigoUF,
            @Valid @ValidNumeric @RequestParam(required = false) Integer status){

        List<UF> ufs = new ArrayList<>();

        if (codigoUF == null && sigla == null &&  nome == null && status != null){
            ufs = ufService.buscarUFsPorStatus(status);
        }

        else if(codigoUF != null || sigla != null ||  nome != null ){
            UF uf = ufService.buscarUFs(sigla, nome, codigoUF, status);

            UFDto ufDto = new UFDto(uf.getCodigoUF(),
                    uf.getSigla(),
                    uf.getNome(),
                    uf.getStatus());

            return ResponseEntity.ok(ufDto);
        }
        else{
            ufs = ufService.buscarTodasUfs();
        }

        List<UFDto> ufDtos = ufs.stream().map(uf -> {
            UFDto ufDto = new UFDto(uf.getCodigoUF(),
                    uf.getSigla(),
                    uf.getNome(),
                    uf.getStatus());
            return ufDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(ufDtos);
    }

    @PutMapping
    public ResponseEntity<List<UFDto>> atualizarUF(@RequestBody UFDto ufDto) {

        List<UF> ufs = ufService.atualizar(ufDto);

        List<UFDto> ufDtos = ufs.stream().map(uf -> {
            UFDto ufsDto = new UFDto(uf.getCodigoUF(),
                    uf.getSigla(),
                    uf.getNome(),
                    uf.getStatus());
            return ufsDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(ufDtos);
    }

    @DeleteMapping
    public void desativarUFs(
            @Valid @ValidNumeric @RequestParam(required = false) Long codigoUF){

            ufService.desativarUFs(codigoUF);
    }

}
