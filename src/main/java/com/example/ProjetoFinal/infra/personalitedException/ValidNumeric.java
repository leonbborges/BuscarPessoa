package com.example.ProjetoFinal.infra.personalitedException;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ValidNumericValidator.class) // Classe validadora
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Pode ser usada em campos e parâmetros
@Retention(RetentionPolicy.RUNTIME) // Disponível em tempo de execução
public @interface ValidNumeric {

    String message() default "O valor deve ser numérico válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}