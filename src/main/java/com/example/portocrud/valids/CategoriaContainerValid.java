package com.example.portocrud.valids;

import com.example.portocrud.validators.CategoriaContainerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoriaContainerValidator.class)
public @interface CategoriaContainerValid {
    String message() default "Valor inválido para categoria de container, a categoria deve ser IMPORTACAO ou EXPORTACAO";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
