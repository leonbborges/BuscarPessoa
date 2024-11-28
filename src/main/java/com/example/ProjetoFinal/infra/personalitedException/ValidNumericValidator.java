package com.example.ProjetoFinal.infra.personalitedException;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidNumericValidator implements ConstraintValidator<ValidNumeric, Object> {

    @Override
    public void initialize(ValidNumeric constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Valores nulos são considerados válidos para o caso de filtros
        }

        // Aceitar apenas valores do tipo Long ou Integer
        if (value instanceof Long || value instanceof Integer) {
            return true;
        }

        return false;
    }
}
