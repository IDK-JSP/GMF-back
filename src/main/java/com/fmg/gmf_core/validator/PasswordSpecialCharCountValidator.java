package com.fmg.gmf_core.validator;

import com.fmg.gmf_core.annotation.ValidPasswordSpecialCharCount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordSpecialCharCountValidator implements ConstraintValidator<ValidPasswordSpecialCharCount, String> {
    public boolean isValid(String value, ConstraintValidatorContext context) {
        long specialCharCount = value.chars()
                .filter(c -> !Character.isLetterOrDigit(c))
                .count();
        if (specialCharCount < 2) {
            return false;
        }

        return true;
    }
}
