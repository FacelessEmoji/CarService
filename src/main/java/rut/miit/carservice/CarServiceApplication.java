package rut.miit.carservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rut.miit.carservice.dtos.complex.OfferWithDetailsDTO;
import rut.miit.carservice.dtos.input.*;
import rut.miit.carservice.dtos.output.CarModelOutputDTO;
import rut.miit.carservice.dtos.output.UserOutputDTO;
import rut.miit.carservice.models.entities.*;

@SpringBootApplication
public class CarServiceApplication {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
//
//        // CarBrand to CarBrandDTO
//        modelMapper.createTypeMap(CarBrand.class, CarBrandDTO.class);
//
//        // CarModel to CarModelDTO
//        modelMapper.createTypeMap(CarModel.class, CarModelDTO.class);
//
//        // CarModel to CarModelWithBrandDTO
//        TypeMap<CarModel, CarModelOutputDTO> typeMapCarModelWithBrand = modelMapper.createTypeMap(CarModel.class, CarModelOutputDTO.class);
//        typeMapCarModelWithBrand.addMappings(m->m.map(CarModel::getBrand, CarModelOutputDTO::setCarBrandName));
//
//        // User to UserDTO
//        modelMapper.createTypeMap(User.class, UserDTO.class);
//
//        // UserRole to UserRoleDTO
//        modelMapper.createTypeMap(UserRole.class, UserRoleDTO.class);
//
//        // User to UserWithRoleDTO
//        TypeMap<User, UserOutputDTO> typeMapUserWithRole = modelMapper.createTypeMap(User.class, UserOutputDTO.class);
//        typeMapUserWithRole.addMappings(m -> m.map(User::getRole, UserOutputDTO::setRole));
//
//        // Offer to OfferDTO
//        modelMapper.createTypeMap(Offer.class, OfferDTO.class);
//
//        // Offer to OfferWithDetailsDTO
//        TypeMap<Offer, OfferWithDetailsDTO> typeMapOfferDetails = modelMapper.createTypeMap(Offer.class, OfferWithDetailsDTO.class);
//        typeMapOfferDetails.addMappings(m->m.map(src -> src.getModel().getName(), OfferWithDetailsDTO::setModelName));
//        typeMapOfferDetails.addMappings(m->m.map(src -> src.getModel().getBrand().getName(), OfferWithDetailsDTO::setBrandName));
//        typeMapOfferDetails.addMappings(m->m.map(src -> src.getSeller().getUsername(), OfferWithDetailsDTO::setSellerUsername));
//        typeMapOfferDetails.addMappings(m->m.map(src -> src.getSeller().getIsActive(), OfferWithDetailsDTO::setIsActive));
        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

}

