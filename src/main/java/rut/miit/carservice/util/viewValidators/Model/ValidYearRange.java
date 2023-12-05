package rut.miit.carservice.util.viewValidators.Model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//старт меньше энд и они меньше значений
public @interface ValidYearRange {
    String message() default "Years should be between 1900 and 2100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}