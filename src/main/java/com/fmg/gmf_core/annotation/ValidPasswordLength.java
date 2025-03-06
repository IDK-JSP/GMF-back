package com.fmg.gmf_core.annotation;

import com.fmg.gmf_core.validator.PasswordLengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordLengthValidator.class) // Associe l'annotation au validateur
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Peut être utilisée sur un champ ou un paramètre
@Retention(RetentionPolicy.RUNTIME) // Disponible à l'exécution
public @interface ValidPasswordLength {
    String message() default "Le mot de passe doit contenir au moins 12 caractères.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
