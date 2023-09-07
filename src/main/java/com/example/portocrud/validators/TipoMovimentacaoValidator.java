package com.example.portocrud.validators;

import com.example.portocrud.enums.TipoMovimentacao;
import com.example.portocrud.valids.TipoMovimentacaoValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoMovimentacaoValidator implements ConstraintValidator<TipoMovimentacaoValid, String> {

    @Override
    public void initialize(TipoMovimentacaoValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Valor nulo é considerado válido, caso deseje tratar como inválido, remova esta condição.
        }

        try {
            TipoMovimentacao tipo = TipoMovimentacao.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Valor não corresponde a nenhum valor do enum
        }
    }
}