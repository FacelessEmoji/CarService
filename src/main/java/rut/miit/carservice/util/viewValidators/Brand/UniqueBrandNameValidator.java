package rut.miit.carservice.util.viewValidators.Brand;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import rut.miit.carservice.repositories.CarBrandRepository;

/**
 * todo Document type UniqueBrandNameValidator
 */
public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {

    @Autowired
    private CarBrandRepository brandRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && brandRepository.findByName(value) == null;
    }
}

