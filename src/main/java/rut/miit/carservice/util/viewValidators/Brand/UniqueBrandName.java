package rut.miit.carservice.util.viewValidators.Brand;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * todo Document type UniqueBrandName
 */
@Documented
@Constraint(validatedBy = UniqueBrandNameValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueBrandName {
    String message() default "Brand name must be unique!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

