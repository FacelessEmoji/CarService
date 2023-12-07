package rut.miit.carservice.util.viewValidators.User;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "Username must be unique!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
