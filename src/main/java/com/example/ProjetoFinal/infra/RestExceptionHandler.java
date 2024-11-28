package com.example.ProjetoFinal.infra;

import com.example.ProjetoFinal.infra.exception.Bairro.BairroInsertException;
import com.example.ProjetoFinal.infra.exception.Bairro.BairroNullParamException;
import com.example.ProjetoFinal.infra.exception.Bairro.NotFoundBairroException;
import com.example.ProjetoFinal.infra.exception.Endereco.EnderecoNullParamException;
import com.example.ProjetoFinal.infra.exception.Municipio.MunicipioInsertException;
import com.example.ProjetoFinal.infra.exception.Municipio.MunicipioNullParamException;
import com.example.ProjetoFinal.infra.exception.Municipio.NotFoundMunicipioException;
import com.example.ProjetoFinal.infra.exception.Pessoa.NotFoundPessoaException;
import com.example.ProjetoFinal.infra.exception.Pessoa.PessoaInsertException;
import com.example.ProjetoFinal.infra.exception.Pessoa.PessoaNullParamException;
import com.example.ProjetoFinal.infra.exception.UF.NotFoundUFException;
import com.example.ProjetoFinal.infra.exception.UF.UFInsertException;
import com.example.ProjetoFinal.infra.exception.UF.UFNullParamException;
import com.example.ProjetoFinal.infra.message.RestErroMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UFInsertException.class)
    public ResponseEntity<RestErroMessage> UFInsert(UFInsertException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(UFNullParamException.class)
    public ResponseEntity<RestErroMessage> UFNullParam(UFNullParamException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(NotFoundUFException.class)
    public ResponseEntity<String> NotFoundUFExcpetion(NotFoundUFException exception){
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(MunicipioInsertException.class)
    public ResponseEntity<RestErroMessage> MunicipioInsert(MunicipioInsertException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(MunicipioNullParamException.class)
    public ResponseEntity<RestErroMessage> MunicipioNullParam(MunicipioNullParamException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(NotFoundMunicipioException.class)
    public ResponseEntity<String> NotFoundMunicipioExcpetion(NotFoundMunicipioException exception){
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(BairroInsertException.class)
    public ResponseEntity<RestErroMessage> BairroInsert(BairroInsertException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }


    @ExceptionHandler(NotFoundBairroException.class)
    public ResponseEntity<String> NotFoundBairroExcpetion(NotFoundBairroException exception){
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(BairroNullParamException.class)
    public ResponseEntity<RestErroMessage> BairroNullParam (BairroNullParamException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(PessoaInsertException.class)
    public ResponseEntity<RestErroMessage> PessoaInsert(PessoaInsertException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(NotFoundPessoaException.class)
    public ResponseEntity<String> NotFoundPessoaExcpetion(NotFoundPessoaException exception){
        return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
    }

    @ExceptionHandler(PessoaNullParamException.class)
    public ResponseEntity<RestErroMessage> PessoaNullParam (PessoaNullParamException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }

    @ExceptionHandler(EnderecoNullParamException.class)
    public ResponseEntity<RestErroMessage> EnderecoNullParam (EnderecoNullParamException exception){
        RestErroMessage theathResponse = new RestErroMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theathResponse);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestErroMessage> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String paramName = ex.getName();
        String message = String.format("O parâmetro '%s' deve ser um valor numérico.", paramName);

        RestErroMessage errorResponse = new RestErroMessage(message, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
