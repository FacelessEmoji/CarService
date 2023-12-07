package rut.miit.carservice.util.viewValidators.User;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "The name must begin with a capital letter and must contain only letters and spaces!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
