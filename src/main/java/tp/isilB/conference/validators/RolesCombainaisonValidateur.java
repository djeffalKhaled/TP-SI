package tp.isilB.conference.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tp.isilB.conference.entities.Role;

import java.util.Collection;

/* Allowed roles:
* Editeur > Auteur OU Evaluateur OU les deux
* Evaluateur > Auteur
* */
public class RolesCombainaisonValidateur implements ConstraintValidator<RolesValid, Collection<Role>> {

    @Override
    public boolean isValid(Collection<Role> roles, ConstraintValidatorContext context) {
        if (roles == null || roles.isEmpty()) {
            return false;
        } else if (roles.toArray()[0].toString().contains("ROLE_ADMIN")) {
            return true;
        }
        System.out.println("API: Checking role validation: " + roles.toString());

        // The first role is assumed as the MAIN ROLE
        if ((roles.toArray()[0].toString().contains("nomRole=ROLE_AUTEUR"))) {
            return roles.size() == 1; // it MUST be a single role (auteur peut pas etre un evaluateur)
        } else if ((roles.toArray()[0].toString().contains("nomRole=ROLE_EDITEUR"))) {
            return true; // it can be ANY role (editeur peut etre auteur ou evaluateur)
        } else if ((roles.toArray()[0].toString().contains("nomRole=ROLE_EVALUATEUR"))) {
            return (roles.toArray()[1].toString().contains("nomRole=ROLE_AUTEUR") && roles.size() == 2) || roles.size() == 1; // it can be either a single role OR also an Auteur
        }

        return false;
    }
}
