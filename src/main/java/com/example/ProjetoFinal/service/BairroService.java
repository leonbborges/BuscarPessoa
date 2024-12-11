package com.example.ProjetoFinal.service;

import com.example.ProjetoFinal.controller.dto.BairroDto;
import com.example.ProjetoFinal.entity.Bairro;
import com.example.ProjetoFinal.entity.Municipio;
import com.example.ProjetoFinal.infra.exception.Bairro.BairroInsertException;
import com.example.ProjetoFinal.infra.exception.Bairro.NotFoundBairroException;
import com.example.ProjetoFinal.infra.exception.Municipio.MunicipioInsertException;
import com.example.ProjetoFinal.infra.exception.Municipio.NotFoundMunicipioException;
import com.example.ProjetoFinal.infra.handler.utils.validation.validarBairro;
import com.example.ProjetoFinal.repository.BairroRepository;
import com.example.ProjetoFinal.repository.MunicipioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    public Bairro buscarBairros(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {
        return bairroRepository.findOneByCriteria(codigoBairro, codigoMunicipio, nome, status)
                .orElseThrow(
                        () -> new NotFoundBairroException("[]"));
    }

    public List<Bairro> buscarBairroPorAtributos(Long codigoMunicipio, String nome, Integer status) {
        return bairroRepository.searchByParameters(codigoMunicipio, nome, status);
    }

    public List<Bairro> buscarTodosOsBairros() {
        return bairroRepository.findAll();
    }

    public List<Bairro> salvarBairro(BairroDto bairroDto) {

        validarBairro.validarEntradaBairro(bairroDto);
        Municipio municipioRetorno = municipioRepository.findById(bairroDto.getCodigoMunicipio())
                .orElseThrow(() -> new MunicipioInsertException
                        ("não foi possivel encontrar um Municipio como o " +
                        "codigo disponibilizado"));

        Bairro bairro = new Bairro(municipioRetorno,
                bairroDto.getNome(),
                bairroDto.getStatus());

        bairroRepository.findByMunicipioCodigoMunicipioAndNome(bairro.getMunicipio().getCodigoMunicipio(),
                bairro.getNome()).ifPresent(existingBairro -> {
                throw new BairroInsertException(
                        "Não foi possível incluir Bairro no banco de dados. Motivo: já existe um registro de Bairro " +
                                "com o nome " + bairro.getNome() + " e o mesmo codigo Municipio:"
                                + bairro.getMunicipio().getCodigoMunicipio() + " cadastrado(a) no banco de dados."
                );
        });

        bairroRepository.save(bairro);

        return bairroRepository.findAll();
    }

    public List<Bairro> AtualizarBairro(BairroDto bairroDto) {

        validarBairro.validarEntradaBairro(bairroDto);
        validarBairro.validarCogigoMunicipio(bairroDto);

        Municipio municipioRetorno = municipioRepository.findById(bairroDto.getCodigoMunicipio())
                .orElseThrow(() -> new MunicipioInsertException("não foi possivel encontrar uma UF como o " +
                        "codigo disponibilizado"));

        Bairro bairro = bairroRepository.findById(bairroDto.getCodigoBairro())
                .orElseThrow(() -> new BairroInsertException("não foi possivel encontrar um Bairro como o " +
                        "codigo disponibilizado"));

        bairroRepository.findByMunicipioCodigoMunicipioAndNome(bairroDto.getCodigoMunicipio()
                , bairroDto.getNome()).ifPresent(existingBairro -> {
            if (!existingBairro.getCodigoBairro().equals(bairroDto.getCodigoBairro())) {
                throw new MunicipioInsertException(
                        "Não foi possível incluir Bairro no banco de dados. Motivo: já existe um registro de Bairro " +
                                "com o nome " + bairro.getNome() + " e o mesmo " + bairro.getMunicipio()
                                .getCodigoMunicipio() + " cadastrado(a) no banco de dados."
                );
            }
        });

        bairro.setMunicipio(municipioRetorno);
        bairro.setNome(bairroDto.getNome());
        bairro.setStatus(bairroDto.getStatus());

        bairroRepository.save(bairro);

        return bairroRepository.findAll();
    }
}
