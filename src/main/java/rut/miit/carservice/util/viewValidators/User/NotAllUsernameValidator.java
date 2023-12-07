package rut.miit.carservice.util.viewValidators.User;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NotAllUsernameValidator implements ConstraintValidator<NotAllUsername, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ! "all".equalsIgnoreCase(value);
    }
}

