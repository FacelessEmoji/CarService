package rut.miit.carservice.init;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rut.miit.carservice.dtos.input.*;
import rut.miit.carservice.models.enums.*;
import rut.miit.carservice.repositories.OfferRepository;
import rut.miit.carservice.services.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
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

        userService.addNewUser(new UserDTO("test1", "111", "Nick", "Jackson", true, "https://82.126.59.6:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("test2", "qwerty123", "test", "test", true, "https://42.146.59.6:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("martin23", "passw123", "Martin", "Gates", true, "https://82.126.59.5:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("julia45", "mysecretpass", "Julia", "Roberts", true, "https://42.146.59.7:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("andrew76", "andrewpass", "Andrew", "Smith", true, "https://82.126.59.4:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("samuel89", "password89", "Samuel", "Johnson", true, "https://42.146.59.8:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("diana01", "dianapass", "Diana", "Brown", true, "https://82.126.59.3:80", userRoleService.getRoleByName(UserRoleType.ADMIN)));
        userService.addNewUser(new UserDTO("oliver55", "olivercool", "Oliver", "Twist", true, "https://42.146.59.9:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("isabella99", "bella123", "Isabella", "Turner", false, "https://82.126.59.2:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("jacob21", "jacobbob", "Jacob", "Moore", false, "https://42.146.59.10:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("emily78", "emilypass", "Emily", "Clark", true, "https://82.126.59.1:80", userRoleService.getRoleByName(UserRoleType.USER)));
        userService.addNewUser(new UserDTO("michael34", "mikepass", "Michael", "Bell", true, "https://42.146.59.11:80", userRoleService.getRoleByName(UserRoleType.USER)));

        carBrandService.addNewBrand(new CarBrandDTO("Toyota"));
        carBrandService.addNewBrand(new CarBrandDTO("BMW"));
        carBrandService.addNewBrand(new CarBrandDTO("Tesla"));
        carBrandService.addNewBrand(new CarBrandDTO("Mercedes"));
        carBrandService.addNewBrand(new CarBrandDTO("Audi"));

        carModelService.addNewModel(new CarModelDTO("Model X", ModelCategory.CAR,"https://82.146.59.6:80",2015,2023,carBrandService.getBrandByName("Tesla")));
        carModelService.addNewModel(new CarModelDTO("3 Series", ModelCategory.CAR,"https://82.146.59.6:81",2010,2023,carBrandService.getBrandByName("BMW")));
        carModelService.addNewModel(new CarModelDTO("A4", ModelCategory.CAR,"https://82.146.59.6:82",2012,2023,carBrandService.getBrandByName("Audi")));
        carModelService.addNewModel(new CarModelDTO("Model S", ModelCategory.CAR,"https://82.146.59.6:83",2014,2023,carBrandService.getBrandByName("Tesla")));
        carModelService.addNewModel(new CarModelDTO("Model 3", ModelCategory.CAR,"https://82.146.59.6:84",2017,2023,carBrandService.getBrandByName("Tesla")));
        carModelService.addNewModel(new CarModelDTO("C-Class", ModelCategory.CAR,"https://82.146.59.6:85",2008,2023,carBrandService.getBrandByName("Mercedes")));
        carModelService.addNewModel(new CarModelDTO("Camry", ModelCategory.CAR,"https://82.146.59.6:86",2005,2023,carBrandService.getBrandByName("Toyota")));
        carModelService.addNewModel(new CarModelDTO("Model Y", ModelCategory.CAR,"https://82.146.59.6:87",2019,2023,carBrandService.getBrandByName("Tesla")));
        carModelService.addNewModel(new CarModelDTO("E-Class", ModelCategory.CAR,"https://82.146.59.6:88",2007,2023,carBrandService.getBrandByName("Mercedes")));
        carModelService.addNewModel(new CarModelDTO("A3", ModelCategory.CAR,"https://82.146.59.6:89",2015,2023,carBrandService.getBrandByName("Audi")));
        carModelService.addNewModel(new CarModelDTO("Corolla", ModelCategory.CAR,"https://82.146.59.6:90",2003,2023,carBrandService.getBrandByName("Toyota")));
        carModelService.addNewModel(new CarModelDTO("A6", ModelCategory.CAR,"https://82.146.59.6:91",2009,2023,carBrandService.getBrandByName("Audi")));
        carModelService.addNewModel(new CarModelDTO("5 Series", ModelCategory.CAR,"https://82.146.59.6:92",2011,2023,carBrandService.getBrandByName("BMW")));
        carModelService.addNewModel(new CarModelDTO("Land Cruiser", ModelCategory.TRUCK,"https://82.146.59.6:93",2002,2023,carBrandService.getBrandByName("Toyota")));
        carModelService.addNewModel(new CarModelDTO("Model Roadster", ModelCategory.CAR,"https://82.146.59.6:94",2010,2015,carBrandService.getBrandByName("Tesla")));

        offerService.addNewOffer(new OfferDTO("Test Description", EngineType.ELECTRIC,
                "https://82.146.90.6:80",13744, new BigDecimal("45700"), TransmissionType.AUTOMATIC,
                2022, carModelService.getModelByNameAndBrand("Model X", "Tesla"),userService.getUserByUsername("test1")));
        offerService.addNewOffer(new OfferDTO("Brand New BMW", EngineType.GASOLINE,
                "https://82.146.90.6:81",5000, new BigDecimal("50200"), TransmissionType.AUTOMATIC,
                2021, carModelService.getModelByNameAndBrand("3 Series", "BMW"),userService.getUserByUsername("test1")));
        offerService.addNewOffer(new OfferDTO("Elegant Mercedes", EngineType.DIESEL,
                "https://82.146.90.6:82",8000, new BigDecimal("56000"), TransmissionType.AUTOMATIC,
                2021, carModelService.getModelByNameAndBrand("C-Class", "Mercedes"),userService.getUserByUsername("julia45")));
        offerService.addNewOffer(new OfferDTO("Sleek Tesla Model S", EngineType.ELECTRIC,
                "https://82.146.90.6:83",7000, new BigDecimal("80000"), TransmissionType.AUTOMATIC,
                2020, carModelService.getModelByNameAndBrand("Model S", "Tesla"),userService.getUserByUsername("andrew76")));
        offerService.addNewOffer(new OfferDTO("Reliable Toyota Camry", EngineType.GASOLINE,
                "https://82.146.90.6:84",12000, new BigDecimal("28000"), TransmissionType.AUTOMATIC,
                2018, carModelService.getModelByNameAndBrand("Camry", "Toyota"),userService.getUserByUsername("samuel89")));
        offerService.addNewOffer(new OfferDTO("Stylish Audi A4", EngineType.DIESEL,
                "https://82.146.90.6:85",9000, new BigDecimal("45000"), TransmissionType.AUTOMATIC,
                2019, carModelService.getModelByNameAndBrand("A4", "Audi"),userService.getUserByUsername("diana01")));
        offerService.addNewOffer(new OfferDTO("Powerful Tesla Model 3", EngineType.ELECTRIC,
                "https://82.146.90.6:86",5000, new BigDecimal("49000"), TransmissionType.AUTOMATIC,
                2022, carModelService.getModelByNameAndBrand("Model 3", "Tesla"),userService.getUserByUsername("oliver55")));
        offerService.addNewOffer(new OfferDTO("Comfortable Audi A6", EngineType.GASOLINE,
                "https://82.146.90.6:87",13000, new BigDecimal("58000"), TransmissionType.AUTOMATIC,
                2017, carModelService.getModelByNameAndBrand("A6", "Audi"),userService.getUserByUsername("isabella99")));
    }

    @Override
    public void run(String... args){
        seedData();
        System.out.println("Test");
        LocalDateTime startTime = LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        offerService.getOffersCreatedBetweenDates(startTime, endTime).forEach(System.out::println);
        System.out.println();
        offerService.getOffersByModelAndBrand("Model X", "Tesla").forEach(System.out::println);
        System.out.println();
        offerService.getOffersBySellerUsername("test1").forEach(System.out::println);
        System.out.println();
        carModelService.getModelsByBrandAndYears("Tesla", 2015, 2023).forEach(System.out::println);
        System.out.println();
        carModelService.getModelsByCriteria(ModelCategory.CAR, EngineType.GASOLINE, TransmissionType.AUTOMATIC,
                1000000, new BigDecimal(10000000)).forEach(System.out::println);
    }
}

