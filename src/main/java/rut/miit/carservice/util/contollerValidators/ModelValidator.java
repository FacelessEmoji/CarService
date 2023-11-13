package rut.miit.carservice.util.contollerValidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.services.implementations.CarModelServiceImpl;

/**
 * todo Document type ModelValidator
 */
@Component
public class ModelValidator implements Validator {
    private final CarModelServiceImpl modelService;

    @Autowired
    public ModelValidator(CarModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @Override
    public boolean supports(Class<?> someClass) {
        return CarModelDTO.class.equals(someClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CarModelDTO modelDTO = (CarModelDTO) target;

        // Проверяем, что name в пределах бренда уникален
        if (modelService.getModelByBrandAndName(
            modelDTO.getBrandDTO().getName(),
            modelDTO.getName())
            != null) {
            errors.rejectValue("name", "Name.unique", "The names of models of the same brand must be unique");
        }

        // Проверяем, что name не может быть “all”
        if ("all".equals(modelDTO.getName())) {
            errors.rejectValue("name", "Name.all", "Name can't be 'all'");
        }

        if (modelDTO.getName().length() < 2 || modelDTO.getName().length() > 50) {
            errors.rejectValue("name", "CarModel.name.length", "Car model name must be between 2 and 50 characters");
        }

    }
}
