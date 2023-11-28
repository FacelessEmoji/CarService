package rut.miit.carservice.util.viewValidators.Brand;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rut.miit.carservice.repositories.CarBrandRepository;

/**
 * todo Document type UniqueBrandNameValidator
 */
@Component
public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {

    private final CarBrandRepository brandRepository;

    @Autowired
    public UniqueBrandNameValidator(CarBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return brandRepository.findByName(value) == null;
    }
}


