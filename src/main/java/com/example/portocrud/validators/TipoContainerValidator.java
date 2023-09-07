package com.example.portocrud.validators;

import com.example.portocrud.enums.TipoContainer;
import com.example.portocrud.valids.TipoContainerValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoContainerValidator implements ConstraintValidator<TipoContainerValid, Integer> {

    @Override
    public void initialize(TipoContainerValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Valor nulo é considerado inválido
        }

        TipoContainer[] tipos = TipoContainer.values();
        for (TipoContainer tipo : tipos) {
            if (tipo.getNumVal() == value) {
                return true; // O valor corresponde a um valor válido do enum
            }
        }

        return false; // O valor não corresponde a nenhum valor do enum
    }
}
