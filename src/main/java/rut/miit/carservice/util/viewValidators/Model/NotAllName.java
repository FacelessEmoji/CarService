package rut.miit.carservice.util.viewValidators.Model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = NotAllNameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAllName {
    String message() default "Name can't be 'all'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
