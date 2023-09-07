package com.example.portocrud.validators;

import com.example.portocrud.enums.CategoriaContainer;
import com.example.portocrud.valids.CategoriaContainerValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoriaContainerValidator implements ConstraintValidator<CategoriaContainerValid, String> {

    @Override
    public void initialize(CategoriaContainerValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Valor nulo é considerado válido, caso deseje tratar como inválido, remova esta condição.
        }

        try {
            CategoriaContainer categoria = CategoriaContainer.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Valor não corresponde a nenhum valor do enum
        }
    }
}