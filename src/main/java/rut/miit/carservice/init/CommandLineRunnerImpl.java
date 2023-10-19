package rut.miit.carservice.init;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rut.miit.carservice.dtos.input.*;
import rut.miit.carservice.repositories.*;
import rut.miit.carservice.models.entities.*;
import rut.miit.carservice.models.enums.*;
import rut.miit.carservice.services.*;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private CarBrandService<UUID> carBrandService;

    @Autowired
    private CarModelService<UUID> carModelService;

    @Autowired
    private OfferService<UUID> offerService;

    @Autowired
    private UserService<UUID> userService;
    @Autowired
    private UserRoleService<UUID> userRoleService;

    private ModelMapper modelMapper;


    public void seedData() {
        userRoleService.addNewRole(new UserRoleDTO(UserRoleType.USER));
        userRoleService.addNewRole(new UserRoleDTO(UserRoleType.ADMIN));

        userService.addNewUser(new UserDTO( "test1", "111", "Nick", "Jackson", true, "https://82.126.59.6:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO( "test2", "qwerty123", "test", "test", true, "https://42.146.59.6:80", userRoleService.getRoleByName(UserRoleType.USER)));

        carBrandService.addNewBrand(new CarBrandDTO("Toyota"));
        carBrandService.addNewBrand(new CarBrandDTO("BMW"));
        carBrandService.addNewBrand(new CarBrandDTO("Tesla"));

        carModelService.addNewModel(new CarModelDTO("Model X", ModelCategory.CAR,"https://82.146.59.6:80",2015,2023,carBrandService.getBrandByName("Tesla")));

        offerService.addNewOffer(new OfferDTO("Test Description", EngineType.ELECTRIC,
                "https://82.146.90.6:80",13744, new BigDecimal("45700"), TransmissionType.AUTOMATIC,
                2022, carModelService.getModelByNameAndBrand("Model X", "Tesla"),userService.getUserByUsername("test1")));

    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }
}

