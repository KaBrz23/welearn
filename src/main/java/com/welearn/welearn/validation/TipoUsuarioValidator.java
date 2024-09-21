package com.welearn.welearn.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoUsuarioValidator implements ConstraintValidator<TipoUsuario, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("Aluno") || value.equals("Instrutor");
    }
}