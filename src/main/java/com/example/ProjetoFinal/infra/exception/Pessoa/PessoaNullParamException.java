package com.example.ProjetoFinal.infra.exception.Pessoa;

public class PessoaNullParamException extends RuntimeException{
    public PessoaNullParamException(String message){
        super(message);
    }
}
