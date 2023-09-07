package com.example.portocrud.valids;

import com.example.portocrud.validators.TipoContainerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoContainerValidator.class)
public @interface TipoContainerValid {
    String message() default "Valor inválido para tipo de container. Deve ser 20 ou 40.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

