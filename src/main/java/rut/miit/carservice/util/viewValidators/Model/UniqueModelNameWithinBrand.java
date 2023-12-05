package rut.miit.carservice.util.viewValidators.Model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueModelNameWithinBrandValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueModelNameWithinBrand {
    String message() default "The names of models of the same brand must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
