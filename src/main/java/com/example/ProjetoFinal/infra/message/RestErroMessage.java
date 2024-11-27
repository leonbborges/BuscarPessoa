package com.example.ProjetoFinal.infra.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestErroMessage {

    private String message;
    private int satatus;

}
