package com.welearn.welearn.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoUsuarioValidator.class)
public @interface TipoUsuario {
    String message() default "O usuário deve ser aluno ou instrutor";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}