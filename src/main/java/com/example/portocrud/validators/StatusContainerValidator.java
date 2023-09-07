package com.example.portocrud.validators;

import com.example.portocrud.enums.StatusContainer;
import com.example.portocrud.valids.StatusContainerValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusContainerValidator implements ConstraintValidator<StatusContainerValid, String> {

    @Override
    public void initialize(StatusContainerValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Valor nulo é considerado válido, caso deseje tratar como inválido, remova esta condição.
        }

        try {
            StatusContainer status = StatusContainer.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Valor não corresponde a nenhum valor do enum
        }
    }
}