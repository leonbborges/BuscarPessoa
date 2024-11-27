package com.example.ProjetoFinal.infra.exception.Pessoa;

public class NotFoundPessoaException extends RuntimeException{
    public NotFoundPessoaException(String message){
        super(message);
    }
}
