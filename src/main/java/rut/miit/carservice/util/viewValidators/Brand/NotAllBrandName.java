package rut.miit.carservice.util.viewValidators.Brand;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
/**
 * todo Document type NotAllBrandName
 */
@Documented
@Constraint(validatedBy = NotAllBrandNameValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAllBrandName {
    String message() default "Brand name can't be 'all'!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

