package com.example.ProjetoFinal.controller;

import com.example.ProjetoFinal.controller.dto.BairroDto;
import com.example.ProjetoFinal.entity.Bairro;
import com.example.ProjetoFinal.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @GetMapping
    public ResponseEntity<?> listarBairrosFiltrados(
            @RequestParam(required = false) Long codigoBairro,
            @RequestParam(required = false) Long codigoMunicipio,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer status){

        List<Bairro> bairros =  new ArrayList<>();

        if(codigoBairro != null){
            Bairro bairro = bairroService.buscarBairros(codigoBairro, codigoMunicipio, nome, status);

            BairroDto bairroDto = new BairroDto(bairro.getCodigoBairro(),
                    bairro.getMunicipio().getCodigoMunicipio(),
                    bairro.getNome(),
                    bairro.getStatus());

            return ResponseEntity.ok(bairroDto);
        }
        else if (nome != null || codigoMunicipio != null || status != null) {
            bairros = bairroService.buscarBairroPorAtributos(codigoMunicipio,nome,status);
        } else {
            bairros = bairroService.buscarTodosOsBairros();
        }

        List<BairroDto> bairroDtos = bairros.stream().map(bairro -> {
            BairroDto bairroDto = new BairroDto(bairro.getCodigoBairro(),
                    bairro.getMunicipio().getCodigoMunicipio(),
                    bairro.getNome(),
                    bairro.getStatus());
            return bairroDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(bairroDtos);
    }

    @PostMapping
    public ResponseEntity<List<BairroDto>> salvarBairro(@RequestBody BairroDto bDto) {

        List<Bairro> bairros = bairroService.salvarBairro(bDto);

        List<BairroDto> bairroDtos = bairros.stream().map(bairro -> {
            BairroDto bairroDto = new BairroDto(bairro.getCodigoBairro(),
                    bairro.getMunicipio().getCodigoMunicipio(),
                    bairro.getNome(),
                    bairro.getStatus());
            return bairroDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(bairroDtos);
    }

    @PutMapping
    public ResponseEntity<List<BairroDto>> atualizarBairro(@RequestBody BairroDto bDto) {

        List<Bairro> bairros= bairroService.AtualizarBairro(bDto);

        List<BairroDto> bairroDtos = bairros.stream().map(bairro -> {
            BairroDto bairroDto = new BairroDto(bairro.getCodigoBairro(),
                    bairro.getMunicipio().getCodigoMunicipio(),
                    bairro.getNome(),
                    bairro.getStatus());
            return bairroDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(bairroDtos);
    }
}
