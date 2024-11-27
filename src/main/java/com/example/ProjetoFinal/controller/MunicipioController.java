package com.example.ProjetoFinal.controller;

import com.example.ProjetoFinal.controller.dto.MunicipioDto;
import com.example.ProjetoFinal.entity.Municipio;
import com.example.ProjetoFinal.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @PostMapping
    public ResponseEntity<List<MunicipioDto>> salvarMunicipio(@RequestBody MunicipioDto mDto) {

        List<Municipio> municipios = municipioService.salvarMunicipio(mDto);

        List<MunicipioDto> municipiosDto = municipios.stream().map(municipio -> {
            MunicipioDto municipioDto = new MunicipioDto(municipio.getCodigoMunicipio(),
                    municipio.getUf().getCodigoUF(),
                    municipio.getNome(),
                    municipio.getStatus());
            return municipioDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(municipiosDto);
    }

    @GetMapping
    public ResponseEntity<?> listarMunicipiosFiltrados(
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) Long CodigoUF,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status){

        List<Municipio> municipios =  new ArrayList<>();

        if(codigoMunicipio != null){
            Municipio municipio = municipioService.buscarMunicipios(codigoMunicipio, CodigoUF, nome, status);

            MunicipioDto municipioDto = new MunicipioDto(municipio.getCodigoMunicipio(),
                    municipio.getUf().getCodigoUF(),
                    municipio.getNome(),
                    municipio.getStatus());

            return ResponseEntity.ok(municipioDto);
        }
        else if (nome != null || CodigoUF != null || status != null) {
            municipios = municipioService.buscarMunicipioPorAtributos(CodigoUF,nome,status);
        } else {
            municipios = municipioService.buscarTodosOsMunicipios();
        }

        List<MunicipioDto> municipiosDto = municipios.stream().map(municipio -> {
            MunicipioDto municipioDto = new MunicipioDto(municipio.getCodigoMunicipio(),
                    municipio.getUf().getCodigoUF(),
                    municipio.getNome(),
                    municipio.getStatus());
            return municipioDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(municipiosDto);

    }


    @PutMapping
    public ResponseEntity<List<MunicipioDto>> atualizarMunicipio(@RequestBody MunicipioDto mDto) {

        List<Municipio> municipios = municipioService.AtualizarMunicipio(mDto);

        List<MunicipioDto> municipiosDto = municipios.stream().map(municipio -> {
            MunicipioDto municipioDto = new MunicipioDto(municipio.getCodigoMunicipio(),
                    municipio.getUf().getCodigoUF(),
                    municipio.getNome(),
                    municipio.getStatus());
            return municipioDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(municipiosDto);
    }
}