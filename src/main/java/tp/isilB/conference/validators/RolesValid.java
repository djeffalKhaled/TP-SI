package tp.isilB.conference.validators;


import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=RolesCombainaisonValidateur.class)
public @interface RolesValid {
    String message() default "Combinaison de rôles non valide!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}