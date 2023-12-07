package rut.miit.carservice.util.viewValidators.User;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return Character.isUpperCase(value.charAt(0)) && value.matches("[a-zA-Z\\s]+");
    }
}

