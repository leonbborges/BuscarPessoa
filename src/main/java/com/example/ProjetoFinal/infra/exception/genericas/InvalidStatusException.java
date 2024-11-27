package com.example.ProjetoFinal.infra.exception.genericas;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String message) {
        super(message);
    }
}