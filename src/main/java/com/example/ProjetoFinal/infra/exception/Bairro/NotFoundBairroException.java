package com.example.ProjetoFinal.infra.exception.Bairro;

public class NotFoundBairroException extends RuntimeException{
    public NotFoundBairroException(String message){
        super(message);
    }
}
