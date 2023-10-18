package rut.miit.carservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rut.miit.carservice.dtos.*;
import rut.miit.carservice.models.entities.*;

@SpringBootApplication
public class CarServiceApplication {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        // CarBrand to CarBrandDTO
        modelMapper.createTypeMap(CarBrand.class, CarBrandDTO.class);

        // CarModel to CarModelDTO
        modelMapper.createTypeMap(CarModel.class, CarModelDTO.class);

        // CarModel to CarModelWithBrandDTO
        TypeMap<CarModel, CarModelWithBrandDTO> typeMapCarModelWithBrand = modelMapper.createTypeMap(CarModel.class, CarModelWithBrandDTO.class);
        typeMapCarModelWithBrand.addMappings(m->m.map(CarModel::getBrand, CarModelWithBrandDTO::setCarBrandName));

        // User to UserDTO
        modelMapper.createTypeMap(User.class, UserDTO.class);

        // UserRole to UserRoleDTO
        modelMapper.createTypeMap(UserRole.class, UserRoleDTO.class);

        // User to UserWithRoleDTO
        TypeMap<User, UserWithRoleDTO> typeMapUserWithRole = modelMapper.createTypeMap(User.class, UserWithRoleDTO.class);
        typeMapUserWithRole.addMappings(m -> m.map(User::getRole, UserWithRoleDTO::setUserRoleName));

        // Offer to OfferDTO
        modelMapper.createTypeMap(Offer.class, OfferDTO.class);

        // Offer to OfferWithDetailsDTO
        TypeMap<Offer, OfferWithDetailsDTO> typeMapOfferDetails = modelMapper.createTypeMap(Offer.class, OfferWithDetailsDTO.class);
        typeMapOfferDetails.addMappings(m->m.map(src -> src.getModel().getName(), OfferWithDetailsDTO::setModelName));
        typeMapOfferDetails.addMappings(m->m.map(src -> src.getModel().getBrand().getName(), OfferWithDetailsDTO::setBrandName));
        typeMapOfferDetails.addMappings(m->m.map(src -> src.getSeller().getUsername(), OfferWithDetailsDTO::setSellerUsername));
        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

}

