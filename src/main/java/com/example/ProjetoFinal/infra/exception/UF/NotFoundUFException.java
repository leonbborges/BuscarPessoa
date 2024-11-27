package com.example.ProjetoFinal.infra.exception.UF;

public class NotFoundUFException extends RuntimeException{
    public NotFoundUFException(String message){
        super(message);
    }
}
