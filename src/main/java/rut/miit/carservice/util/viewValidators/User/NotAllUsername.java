package rut.miit.carservice.util.viewValidators.User;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotAllUsernameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAllUsername {
    String message() default "Username can't be 'all'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
