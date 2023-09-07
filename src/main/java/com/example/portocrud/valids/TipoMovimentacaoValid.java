package com.example.portocrud.valids;

import com.example.portocrud.validators.TipoMovimentacaoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoMovimentacaoValidator.class)
public @interface TipoMovimentacaoValid {
    String message() default "Valor inválido para tipo de movimentaçao, a movimentação deve ser EMBARQUE, DESCARGA, REPOSICIONAMENTO, SCANNER, PESAGEM, GATE_IN ou GATE_OUT";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
