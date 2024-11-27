package com.example.ProjetoFinal.infra.exception.Municipio;

public class NotFoundMunicipioException extends RuntimeException{
    public NotFoundMunicipioException(String message){
        super(message);
    }
}
