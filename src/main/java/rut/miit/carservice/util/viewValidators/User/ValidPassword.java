package rut.miit.carservice.util.viewValidators.User;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password must contain at least one digit and one letter!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

