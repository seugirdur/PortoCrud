package com.example.portocrud.valids;

import com.example.portocrud.validators.CategoriaContainerValidator;
import com.example.portocrud.validators.StatusContainerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusContainerValidator.class)
public @interface StatusContainerValid {
    String message() default "Valor inválido para status de container, o status deve ser CHEIO ou VAZIO";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
