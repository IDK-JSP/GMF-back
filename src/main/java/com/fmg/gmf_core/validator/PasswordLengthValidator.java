package com.fmg.gmf_core.validator;

import com.fmg.gmf_core.annotation.ValidPasswordLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordLengthValidator implements ConstraintValidator<ValidPasswordLength, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() < 12) {
            return false; // Moins de 12 caractères => invalide
        }
        return true;
    }
}
