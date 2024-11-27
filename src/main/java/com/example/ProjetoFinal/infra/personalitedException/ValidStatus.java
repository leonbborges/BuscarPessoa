package com.example.ProjetoFinal.infra.personalitedException;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = StatusValidator.class) // A classe que vai implementar a lógica de validação
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatus {

    String message() default "O valor de status não é válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
