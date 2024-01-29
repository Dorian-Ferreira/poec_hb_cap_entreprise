package fr.dorian_ferreira.cap_entreprise.validator.annotation;

import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNameRepository;
import fr.dorian_ferreira.cap_entreprise.validator.UniqueNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueName {

    Class<? extends EntityNameRepository<?>> repositoryClass();

    String message() default "Nom indisponible";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
