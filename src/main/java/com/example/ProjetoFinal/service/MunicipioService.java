package com.example.ProjetoFinal.service;

import com.example.ProjetoFinal.controller.dto.MunicipioDto;
import com.example.ProjetoFinal.entity.Municipio;
import com.example.ProjetoFinal.entity.UF;
import com.example.ProjetoFinal.infra.exception.Municipio.MunicipioInsertException;
import com.example.ProjetoFinal.infra.exception.Municipio.NotFoundMunicipioException;
import com.example.ProjetoFinal.infra.exception.UF.NotFoundUFException;
import com.example.ProjetoFinal.infra.exception.UF.UFNullParamException;
import com.example.ProjetoFinal.infra.handler.utils.validation.validarMunicipio;
import com.example.ProjetoFinal.repository.MunicipioRepository;
import com.example.ProjetoFinal.repository.UFRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private UFRepository ufRepository;

    public Municipio buscarMunicipios(Long codigoMunicipio, Long CodigoUF, String nome, Integer status) {
        return municipioRepository.findOneByCriteria(codigoMunicipio, CodigoUF, nome, status)
                .orElseThrow(
                        () -> new NotFoundMunicipioException("[]"));
    }

    public List<Municipio> buscarMunicipioPorAtributos(Long codigoUF, String nome, Integer status) {
        System.out.println("passei por aqui");
        return municipioRepository.searchByParameters(codigoUF, nome, status);
    }

    public List<Municipio> buscarTodosOsMunicipios() {
        return municipioRepository.findAll();
    }

    public List<Municipio> salvarMunicipio(MunicipioDto mDto) {
        validarMunicipio.validarEntradaMunicipio(mDto);

        UF ufRetorno = ufRepository.findById(mDto.getCodigoUF())
                .orElseThrow(() -> new NotFoundUFException("não foi possivel encontrar uma UF como o " +
                        "codigo disponibilizado"));

        Municipio municipio = new Municipio(ufRetorno,
                mDto.getNome(),
                mDto.getStatus());



        municipioRepository.findOneByNome(municipio.getNome()).ifPresent(existingMunicipio -> {
            throw new MunicipioInsertException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: já existe um registro de Municipio " +
                            "com o nome " + municipio.getNome() + " cadastrado(a) no banco de dados."
            );
        });

        municipioRepository.save(municipio);

        return municipioRepository.findAll();
    }

    public List<Municipio> AtualizarMunicipio(MunicipioDto mDto) {
        validarMunicipio.validarEntradaMunicipio(mDto);
        validarMunicipio.validarCogigoMunicipio(mDto);

        UF ufRetorno = ufRepository.findById(mDto.getCodigoUF())
                .orElseThrow(() -> new UFNullParamException("não foi possivel encontrar uma UF como o " +
                        "codigo disponibilizado"));

        Municipio municipio = new Municipio(mDto.getCodigoMunicipio(),
                ufRetorno,
                mDto.getNome(),
                mDto.getStatus());


        municipioRepository.findOneByNome(municipio.getNome()).ifPresent(existingMunicipio -> {
            if (!existingMunicipio.getCodigoMunicipio().equals(mDto.getCodigoUF())) {
            throw new MunicipioInsertException(
                    "Não foi possível incluir Municipio no banco de dados. Motivo: já existe um registro de Municipio " +
                            "com o nome" + municipio.getNome() + " cadastrado(a) no banco de dados."
            );
            }
        });

        municipioRepository.save(municipio);

        return municipioRepository.findAll();
    }

}
