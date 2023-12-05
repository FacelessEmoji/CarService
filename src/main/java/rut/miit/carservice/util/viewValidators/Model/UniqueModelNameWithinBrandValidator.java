package rut.miit.carservice.util.viewValidators.Model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rut.miit.carservice.models.entities.CarBrand;
import rut.miit.carservice.repositories.CarBrandRepository;
import rut.miit.carservice.repositories.CarModelRepository;

@Component
public class UniqueModelNameWithinBrandValidator implements ConstraintValidator<UniqueModelNameWithinBrand, String> {

    private final CarModelRepository modelRepository;
    private final CarBrandRepository brandRepository;

    @Autowired
    public UniqueModelNameWithinBrandValidator(CarModelRepository modelRepository, CarBrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }



    @Override
    public boolean isValid(String modelName, ConstraintValidatorContext context) {
        for (CarBrand brand: brandRepository.findAll()) {
            if (modelRepository.findByBrand_NameAndName(brand.getName(), modelName) != null) {
                return false;
            }
        }
        return true;
    }
}