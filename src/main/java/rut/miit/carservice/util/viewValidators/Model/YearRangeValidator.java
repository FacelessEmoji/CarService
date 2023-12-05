package rut.miit.carservice.util.viewValidators.Model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import rut.miit.carservice.services.dtos.input.CarModelDTO;

@Component
public class YearRangeValidator implements ConstraintValidator<ValidYearRange, CarModelDTO> {

    @Override
    public boolean isValid(CarModelDTO carModelDTO, ConstraintValidatorContext context) {
        return carModelDTO.getStartYear() >= 1900 && carModelDTO.getEndYear() <= 2100;
    }
}