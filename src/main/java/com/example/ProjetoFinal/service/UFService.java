package com.example.ProjetoFinal.service;

import com.example.ProjetoFinal.controller.dto.UFDto;
import com.example.ProjetoFinal.entity.UF;
import com.example.ProjetoFinal.infra.exception.UF.NotFoundUFException;
import com.example.ProjetoFinal.infra.exception.UF.UFInsertException;
import com.example.ProjetoFinal.infra.handler.utils.validation.validarUF;
import com.example.ProjetoFinal.repository.UFRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UFService {

    @Autowired
    private UFRepository ufRepository;

    public List<UF> salvarUF(UFDto ufDto) {

        validarUF.validarEntradaUF(ufDto);
        UF ufnovo = new UF(ufDto.getSigla(), ufDto.getNome(),
                ufDto.getStatus());

        ufRepository.findBySigla(ufnovo.getSigla()).ifPresent(existingUF -> {
            throw new UFInsertException(
                    "Não foi possível incluir UF no banco de dados. Motivo: já existe um registro de UF com a sigla " +
                            ufnovo.getSigla() + " cadastrado(a) no banco de dados."
            );
        });

        ufRepository.findByNome(ufnovo.getNome()).ifPresent(existingUF -> {
            throw new UFInsertException(
                    "Não foi possivel incluir nome no banco de dados.Motivo: ja existe um" +
                    " registro de nome com o nome " + ufDto.getNome() + " cadastrado(a) no banco de dados"
            );
        });

        ufRepository.save(ufnovo);
        return ufRepository.findAll();
    }

    public List<UF> buscarUFsPorStatus(Integer status) {
        return ufRepository.findByStatus(status);
    }

    public List<UF> buscarTodasUfs(){
        return ufRepository.findAll();
    }

    public UF buscarUFs(String sigla, String nome, Long codigoUF, Integer status) {
        return ufRepository.findOneByCriteria(sigla, nome, codigoUF, status)
                .orElseThrow(
                        () -> new NotFoundUFException("[]"));
    }

    public List<UF> atualizar(UFDto ufDto){
        validarUF.validarCogigoUF(ufDto);
        validarUF.validarEntradaUF(ufDto);

        UF ufnovo = ufRepository.findById(ufDto.getCodigoUF())
                .orElseThrow(() -> new UFInsertException(
                        "Não foi possível atualizar a UF. Motivo: o registro com o código "
                                + ufDto.getCodigoUF() + " não foi encontrado no banco de dados."
                ));


        ufRepository.findBySigla(ufDto.getSigla()).ifPresent(existingUF -> {
            if (!existingUF.getCodigoUF().equals(ufDto.getCodigoUF())) {
                throw new UFInsertException(
                        "Não foi possível incluir UF no banco de dados. Motivo: já existe um registro de UF com a sigla " +
                                ufDto.getSigla() + " cadastrado(a) no banco de dados."
                );

            }
        });

        ufRepository.findByNome(ufDto.getNome()).ifPresent(existingUF -> {
            if (!existingUF.getCodigoUF().equals(ufDto.getCodigoUF())) {
                throw new UFInsertException(
                        "Não foi possivel incluir nome no banco de dados.Motivo: ja existe um" +
                                " registro de nome com o nome " + ufDto.getNome() + " cadastrado(a) no banco de dados"
                );
            }
        });

        ufnovo.setNome(ufDto.getNome());
        ufnovo.setSigla(ufDto.getSigla());
        ufnovo.setStatus(ufDto.getStatus());

        ufRepository.save(ufnovo);

        return ufRepository.findAll();
    }

    public void desativarUFs(Long codigoUF) {
        UF uf = ufRepository.findById(codigoUF)
                .orElseThrow(
                        () -> new NotFoundUFException("[]"));

        if(uf.getStatus() == 1){
            uf.setStatus(2);
        }
        else if(uf.getStatus() ==2){
            throw new UFInsertException("O Valor do Status já 2, por isso não é possivel atera-lo");
        }
        else{
            throw new UFInsertException("O Valor do Status inserido não é um valor valido");
        }

        ufRepository.save(uf);
    }

}
