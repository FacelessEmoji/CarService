package rut.miit.carservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import rut.miit.carservice.models.entities.*;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.services.dtos.input.OfferDTO;
import rut.miit.carservice.services.dtos.output.*;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        return validatorFactoryBean;
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Mapping from Offer to OfferWithDetailsDTO
        TypeMap<Offer, OfferWithDetailsDTO> offerToDTO = modelMapper.createTypeMap(Offer.class, OfferWithDetailsDTO.class);
        offerToDTO.addMappings(m -> m.map(src -> src.getModel().getBrand().getName(), OfferWithDetailsDTO::setBrandName));
        offerToDTO.addMappings(m -> m.map(src -> src.getModel().getName(), OfferWithDetailsDTO::setModelName));
        offerToDTO.addMappings(m -> m.map(src -> src.getSeller().getUsername(), OfferWithDetailsDTO::setSellerUsername));
        offerToDTO.addMappings(m -> m.map(src -> src.getSeller().getActive(), OfferWithDetailsDTO::setIsActive));

        // Mapping from Offer to OfferDTO
        TypeMap<Offer, OfferDTO> offerToInputDTO = modelMapper.createTypeMap(Offer.class, OfferDTO.class);
        offerToInputDTO.addMappings(m -> m.map(src -> src.getModel().getId(), OfferDTO::setModel));
        offerToInputDTO.addMappings(m -> m.map(src -> src.getSeller().getId(), OfferDTO::setSeller));

        // Mapping from CarModel to CarModelOutputDTO
        TypeMap<CarModel, CarModelOutputDTO> carModelToOutputDTO = modelMapper.createTypeMap(CarModel.class, CarModelOutputDTO.class);
        carModelToOutputDTO.addMappings(m -> m.map(src -> src.getBrand().getName(), CarModelOutputDTO::setCarBrandName));

        //Mapping from CarModel to CarModelDTO
        TypeMap<CarModel, CarModelDTO> carModelToDTO = modelMapper.createTypeMap(CarModel.class, CarModelDTO.class);
        carModelToDTO.addMappings(m -> m.map(src -> src.getBrand().getId(), CarModelDTO::setBrand));

        // Mapping from User to UserOutputDTO
        TypeMap<User, UserOutputDTO> userToOutputDTO = modelMapper.createTypeMap(User.class, UserOutputDTO.class);
        userToOutputDTO.addMappings(m -> m.map(src -> src.getRole().getRole(), UserOutputDTO::setRole));


        return modelMapper;
    }
}
