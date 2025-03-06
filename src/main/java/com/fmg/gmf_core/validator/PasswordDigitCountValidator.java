package com.fmg.gmf_core.validator;

import com.fmg.gmf_core.annotation.ValidPasswordDigitCount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordDigitCountValidator implements ConstraintValidator<ValidPasswordDigitCount,String> {
    public boolean isValid(String value, ConstraintValidatorContext context) {

        long digitCount = value.chars().filter(Character::isDigit).count();
        if (digitCount < 2) {
            return false;
        }
        return true;
    }
}
