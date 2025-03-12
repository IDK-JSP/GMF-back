package com.fmg.gmf_core.annotation;

import com.fmg.gmf_core.validator.FavoriteTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FavoriteTypeValidator.class) // Associe l'annotation au validateur
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Peut être utilisée sur un champ ou un paramètre
@Retention(RetentionPolicy.RUNTIME) // Disponible à l'exécution
public @interface ValidFavoriteType {
    String message() default "Le type de favoris est invalide(recipe ou ingredient).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
