package rut.miit.carservice.util.viewValidators.Model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NotAllNameValidator implements ConstraintValidator<NotAllName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !"all".equalsIgnoreCase(value);
    }
}
