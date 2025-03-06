package com.fmg.gmf_core.annotation;

import com.fmg.gmf_core.validator.EmailValidator;
import com.fmg.gmf_core.validator.FavoriteTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class) // Associe l'annotation au validateur
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Peut être utilisée sur un champ ou un paramètre
@Retention(RetentionPolicy.RUNTIME) // Disponible à l'exécution
public @interface ValidEmail {
    String message() default "L'email est invalide, il doit contenir un '.' est au moins un '@'.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
