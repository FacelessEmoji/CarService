package rut.miit.carservice.util.viewValidators.Brand;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * todo Document type NotAllBrandNameValidator
 */
@Component
public class NotAllBrandNameValidator implements ConstraintValidator<NotAllBrandName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ! "all".equalsIgnoreCase(value);
    }
}

