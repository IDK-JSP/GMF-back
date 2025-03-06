package com.fmg.gmf_core.validator;

import com.fmg.gmf_core.annotation.ValidFavoriteType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FavoriteTypeValidator implements ConstraintValidator<ValidFavoriteType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Ou true selon votre logique de validation pour les valeurs nulles
        }
        // Vérifie si la chaîne contient "recipe" ou "ingredient"
        return value.toLowerCase().contains("recipe") || value.toLowerCase().contains("ingredient");
    }
}
