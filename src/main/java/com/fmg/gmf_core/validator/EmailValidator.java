package com.fmg.gmf_core.validator;

import com.fmg.gmf_core.annotation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Ou true selon votre logique de validation pour les valeurs nulles
        }
        return value.toLowerCase().contains(".") && value.toLowerCase().contains("@");
    }
}
