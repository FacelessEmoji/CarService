package rut.miit.carservice.util.contollerValidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rut.miit.carservice.services.dtos.input.OfferDTO;
import rut.miit.carservice.services.implementations.OfferServiceImpl;

import java.math.BigDecimal;

/**
 * todo Document type OfferValidator
 */
@Component
public class OfferValidator implements Validator {
    private OfferServiceImpl offerService;

    @Autowired
    public OfferValidator(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @Override
    public boolean supports(Class<?> someClass) {
        return OfferDTO.class.equals(someClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OfferDTO offerDTO = (OfferDTO) target;

        if (offerDTO.getDescription().length() < 50 || offerDTO.getDescription().length() > 1000) {
            errors.rejectValue("description", "Offer.description.length", "Offer description must be between 50 and 1000 characters");
        }
        if (offerDTO.getMileage() < 0 || offerDTO.getMileage() > 1000000) {
            errors.rejectValue("mileage", "Offer.mileage.length", "Mileage should be between 0 and 1 000 000");
        }
        //Price should be between 10 and 1 000 000 000
        if (offerDTO.getPrice().compareTo(BigDecimal.valueOf(10)) < 0 || offerDTO.getPrice().compareTo(BigDecimal.valueOf(1000000000)) > 0) {
            errors.rejectValue("price", "Offer.price.length", "Price should be between 10 and 1 000 000 000");
        }

        //year must be between model start year and end year
        if (offerDTO.getYear() < offerDTO.getModel().getStartYear() || offerDTO.getYear() > offerDTO.getModel().getEndYear()) {
            errors.rejectValue("year", "Offer.year.length", "Year must be between model start year and end year");
        }

        //user and model cant be null
        if (offerDTO.getSeller() == null) {
            errors.rejectValue("user", "Offer.user.null", "User cant be null");
        }
        if (offerDTO.getModel() == null) {
            errors.rejectValue("model", "Offer.model.null", "Model cant be null");
        }

    }
}
