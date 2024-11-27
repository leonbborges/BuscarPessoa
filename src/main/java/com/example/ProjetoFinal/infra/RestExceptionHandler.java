package com.example.ProjetoFinal.infra;

import com.example.ProjetoFinal.infra.exception.Bairro.BairroInsertException;
import com.example.ProjetoFinal.infra.exception.Bairro.BairroNullParamException;
import com.example.ProjetoFinal.infra.exception.Bairro.NotFoundBairroException;
import com.example.ProjetoFinal.infra.exception.Municipio.MunicipioInsertException;
import com.example.ProjetoFinal.infra.exception.Municipio.MunicipioNullParamException;
import com.example.ProjetoFinal.infra.exception.Municipio.NotFoundMunicipioException;
import com.example.ProjetoFinal.infra.exception.Pessoa.NotFoundPessoaException;
import com.example.ProjetoFinal.infra.exception.Pessoa.PessoaInsertException;
import com.example.ProjetoFinal.infra.exception.UF.NotFoundUFException;
import com.example.ProjetoFinal.infra.exception.UF.UFInsertException;
import com.example.ProjetoFinal.infra.exception.UF.UFNullParamException;
import com.example.ProjetoFinal.infra.exception.genericas.InvalidStatusException;
import com.example.ProjetoFinal.infra.message.RestErroMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UFInsertException.class)
    private ResponseEntity<RestErroMessage> UFInsert(UFInsertException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(UFNullParamException.class)
    private ResponseEntity<RestErroMessage> UFNullParam(UFNullParamException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(NotFoundUFException.class)
    private ResponseEntity<String> NotFoundUFExcpetion(NotFoundUFException exception){
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(MunicipioInsertException.class)
    private ResponseEntity<RestErroMessage> MunicipioInsert(MunicipioInsertException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(MunicipioNullParamException.class)
    private ResponseEntity<RestErroMessage> MunicipioNullParam(MunicipioNullParamException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(NotFoundMunicipioException.class)
    private ResponseEntity<String> NotFoundMunicipioExcpetion(NotFoundMunicipioException exception){
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(BairroInsertException.class)
    private ResponseEntity<RestErroMessage> BairroInsert(BairroInsertException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(BairroNullParamException.class)
    private ResponseEntity<RestErroMessage> BairroNullParam (BairroNullParamException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(NotFoundBairroException.class)
    private ResponseEntity<String> NotFoundBairroExcpetion(NotFoundBairroException exception){
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(PessoaInsertException.class)
    private ResponseEntity<RestErroMessage> PessoaInsert(PessoaInsertException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(NotFoundPessoaException.class)
    private ResponseEntity<String> NotFoundPessoaExcpetion(NotFoundPessoaException exception){
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidStatusException.class)
    public  ResponseEntity<RestErroMessage>  handleInvalidStatusException(InvalidStatusException exception) {
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }
}
