package rut.miit.carservice.util.contollerValidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.UserServiceImpl;

/**
 * todo Document type BrandValidator
 */
@Component
public class BrandValidator implements Validator {
    private final CarBrandServiceImpl brandService;

    @Autowired
    public BrandValidator(CarBrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @Override
    public boolean supports(Class<?> someClass) {
        return String.class.equals(someClass); // Изменяем тип параметра на String
    }

    @Override
    public void validate(Object target, Errors errors) {
        String brandName = (String) target; // Приводим target к типу String

        // Проверяем, что brandName уникален
        if (brandService.getBrandByName(brandName) != null) {
            errors.reject("Name.unique", "Brand name must be unique");
        }

        // Проверяем, что brandName не может быть “all”
        if ("all".equals(brandName)) {
            errors.reject("Name.all", "Brand name can't be 'all'");
        }

        // Проверяем, что brandName по длине от 2 до 30 символов
        if (brandName.length() < 2 || brandName.length() > 30) {
            errors.reject("Name.length", "Brand name must be from 2 to 30 characters");
        }
    }
}

