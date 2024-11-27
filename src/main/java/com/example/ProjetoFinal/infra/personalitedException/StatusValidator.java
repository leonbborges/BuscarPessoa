package com.example.ProjetoFinal.infra.personalitedException;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<ValidStatus, Integer> {

    @Override
    public void initialize(ValidStatus constraintAnnotation) {
        // Aqui você pode inicializar a lógica de inicialização, se necessário
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 1 && value <= 2;
    }
}
