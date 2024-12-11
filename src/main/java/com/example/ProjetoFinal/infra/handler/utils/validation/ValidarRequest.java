package com.example.ProjetoFinal.infra.handler.utils.validation;

import com.example.ProjetoFinal.controller.dto.UFDto;
import com.example.ProjetoFinal.infra.exception.UF.UFInsertException;
import com.example.ProjetoFinal.infra.exception.UF.UFNullParamException;

public class ValidarRequest {

    public static void validarRequestUF(UFDto dto) {

        if (!(dto.getStatus() instanceof Integer) ){
            throw new UFInsertException(
                    "Esperado um valor numérico no campo status"
            );
        }
    }
}
