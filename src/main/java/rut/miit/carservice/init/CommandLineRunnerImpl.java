package rut.miit.carservice.init;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rut.miit.carservice.models.enums.*;
import rut.miit.carservice.services.dtos.input.*;
import rut.miit.carservice.services.interfaces.publicAPI.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * todo add all to prohibited usernames, models and other
 */
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private CarBrandService<String> carBrandService;

    @Autowired
    private CarModelService<String> carModelService;

    @Autowired
    private OfferService<String> offerService;

    @Autowired
    private UserService<String> userService;

    @Autowired
    private UserRoleService<String> userRoleService;

    @Autowired
    private ModelMapper modelMapper;

    public void seedData() {
        userRoleService.addNewRole(new UserRoleDTO(UserRoleType.USER));
        userRoleService.addNewRole(new UserRoleDTO(UserRoleType.MODERATOR  ));
        userRoleService.addNewRole(new UserRoleDTO(UserRoleType.ADMIN));


        userService.addNewUser(new UserDTO("test1", "111", "Nick", "Jackson", true, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSB1o4IwyEY822wA5mbymjmI-VwPuYo0m33kg&usqp=CAU", userRoleService.getRoleByName(UserRoleType.ADMIN)));
        //userService.addNewUser(new UserDTO("test3", "111", "Nick", "Jackson", true, "https://82.126.59.6:80"));
        userService.addNewUser(new UserDTO("test2", "qwerty123", "test", "test", true, "https://42.146.59.6:80", userRoleService.getRoleByName(UserRoleType.MODERATOR)));
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

        carBrandService.addNewBrand("Toyota");
        carBrandService.addNewBrand("BMW");
        carBrandService.addNewBrand("Tesla");
        carBrandService.addNewBrand("Mercedes");
        carBrandService.addNewBrand("Audi");

        carModelService.addNewModel(new CarModelDTO("Model X", "CAR","https://82.146.59.6:80",2015,2023,carBrandService.getBrandByName("Tesla").getId()));
        carModelService.addNewModel(new CarModelDTO("3 Series", ModelCategory.CAR,"https://82.146.59.6:81",2010,2023,carBrandService.getBrandByName("BMW").getId()));
        carModelService.addNewModel(new CarModelDTO("A4", ModelCategory.CAR,"https://82.146.59.6:82",2012,2023,carBrandService.getBrandByName("Audi").getId()));
        carModelService.addNewModel(new CarModelDTO("Model S", ModelCategory.CAR,"https://82.146.59.6:83",2014,2023,carBrandService.getBrandByName("Tesla").getId()));
        carModelService.addNewModel(new CarModelDTO("Model 3", ModelCategory.CAR,"https://82.146.59.6:84",2017,2023,carBrandService.getBrandByName("Tesla").getId()));
        carModelService.addNewModel(new CarModelDTO("C-Class", ModelCategory.CAR,"https://82.146.59.6:85",2008,2023,carBrandService.getBrandByName("Mercedes").getId()));
        carModelService.addNewModel(new CarModelDTO("Camry", ModelCategory.CAR,"https://82.146.59.6:86",2005,2023,carBrandService.getBrandByName("Toyota").getId()));
        carModelService.addNewModel(new CarModelDTO("Model Y", ModelCategory.CAR,"https://82.146.59.6:87",2019,2023,carBrandService.getBrandByName("Tesla").getId()));
        carModelService.addNewModel(new CarModelDTO("E-Class", ModelCategory.CAR,"https://82.146.59.6:88",2007,2023,carBrandService.getBrandByName("Mercedes").getId()));
        carModelService.addNewModel(new CarModelDTO("A3", ModelCategory.CAR,"https://82.146.59.6:89",2015,2023,carBrandService.getBrandByName("Audi").getId()));
        carModelService.addNewModel(new CarModelDTO("Corolla", ModelCategory.CAR,"https://82.146.59.6:90",2003,2023,carBrandService.getBrandByName("Toyota").getId()));
        carModelService.addNewModel(new CarModelDTO("A6", ModelCategory.CAR,"https://82.146.59.6:91",2009,2023,carBrandService.getBrandByName("Audi").getId()));
        carModelService.addNewModel(new CarModelDTO("5 Series", ModelCategory.CAR,"https://82.146.59.6:92",2011,2023,carBrandService.getBrandByName("BMW").getId()));
        carModelService.addNewModel(new CarModelDTO("Land Cruiser", ModelCategory.TRUCK,"https://82.146.59.6:93",2002,2023,carBrandService.getBrandByName("Toyota").getId()));
        carModelService.addNewModel(new CarModelDTO("Model Roadster", ModelCategory.CAR,"https://82.146.59.6:94",2010,2015,carBrandService.getBrandByName("Tesla").getId()));

        offerService.addNewOffer(new OfferDTO("Test Description", EngineType.ELECTRIC,
                "https://images.drive.ru/i/0/560bf76695a656abb700000a.jpg",13744, new BigDecimal("45700"), TransmissionType.AUTOMATIC,
                2022,carModelService.getModelByBrandAndName("Tesla","Model X").getId(),
            userService.getUserByUsername("test1").getId()));
        offerService.addNewOffer(new OfferDTO("Brand New BMW", EngineType.GASOLINE,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR16ecpWZwF2q-_J2n4Wlk-iKVUWN01gaXIgED0E0_dcA&s",5000, new BigDecimal("50200"), TransmissionType.AUTOMATIC,
                2021, carModelService.getModelByBrandAndName("BMW","3 Series").getId(),
            userService.getUserByUsername("test1").getId()));
        offerService.addNewOffer(new OfferDTO("Elegant Mercedes", EngineType.DIESEL,
                "https://s.auto.drom.ru/i24276/c/photos/fullsize/mercedes-benz/c-class/mercedes-benz_c-class_1105904.jpg",8000, new BigDecimal("56000"), TransmissionType.AUTOMATIC,
                2021, carModelService.getModelByBrandAndName("Mercedes","C-Class").getId(),
            userService.getUserByUsername("julia45").getId()));
        offerService.addNewOffer(new OfferDTO("Sleek Tesla Model S", EngineType.ELECTRIC,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4b1wAzdzYAUDEWjGCAVw47qU-dxaD2FagCw&usqp=CAU",7000, new BigDecimal("80000"), TransmissionType.AUTOMATIC,
                2020, carModelService.getModelByBrandAndName("Tesla", "Model S").getId(),
            userService.getUserByUsername("andrew76").getId()));
        offerService.addNewOffer(new OfferDTO("Reliable Toyota Camry", EngineType.GASOLINE,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQh0XRyM8NfBPn6d1f_Pvq36BQpslyQExF6Q&usqp=CAU",12000, new BigDecimal("28000"), TransmissionType.AUTOMATIC,
                2018, carModelService.getModelByBrandAndName("Toyota", "Camry").getId(),
            userService.getUserByUsername("samuel89").getId()));
        offerService.addNewOffer(new OfferDTO("Stylish Audi A4", EngineType.DIESEL,
                "https://motor.ru/thumb/1500x0/filters:quality(75):no_upscale()/imgs/2021/05/25/14/4682997/113d19fdb9ebef57fae72c796ea0fffc9cee0585.jpg",9000, new BigDecimal("45000"), TransmissionType.AUTOMATIC,
                2019, carModelService.getModelByBrandAndName("Audi", "A4").getId(),
            userService.getUserByUsername("diana01").getId()));
        offerService.addNewOffer(new OfferDTO("Powerful Tesla Model 3", EngineType.ELECTRIC,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT47V7a_A44XEzvMhQ7B3DpT8lR0DTJVxJhdg&usqp=CAU",5000, new BigDecimal("49000"), TransmissionType.AUTOMATIC,
                2022, carModelService.getModelByBrandAndName("Tesla", "Model 3").getId(),
            userService.getUserByUsername("oliver55").getId()));
        offerService.addNewOffer(new OfferDTO("Comfortable Audi A6", EngineType.GASOLINE,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFmwDrcOb23StsuiV7_3Rf4RxbyquqUraYCQ&usqp=CAU",13000, new BigDecimal("58000"), TransmissionType.AUTOMATIC,
                2017, carModelService.getModelByBrandAndName("Audi", "A6").getId(),
            userService.getUserByUsername("isabella99").getId()));
    }

    @Override
    public void run(String... args){
        seedData();
        System.out.println("Test");
        LocalDateTime startTime = LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        offerService.getOffersCreatedBetweenDates(startTime, endTime).forEach(System.out::println);
        System.out.println("-----");
        carModelService.getAllModelsByBrand("Tesla").forEach(System.out::println);
        System.out.println("-----");
        offerService.getOffersByBrandAndModel("Audi", "A4").forEach(System.out::println);
        System.out.println("-----");
        offerService.getOffersBySellerUsername("test1").forEach(System.out::println);
        System.out.println("-----");
        carModelService.getModelsByBrandAndYears("Tesla", 2015, 2023).forEach(System.out::println);
        System.out.println("-----");
        carModelService.getModelsByCriteria(ModelCategory.CAR, EngineType.GASOLINE, TransmissionType.AUTOMATIC,
                1000000, new BigDecimal(10000000)).forEach(System.out::println);
        System.out.println("-----");
        System.out.println(userRoleService.getRoleByName(UserRoleType.USER));
        System.out.println("-----");
        System.out.println(userService.getBaseUserByUsername("test1"));
    }
}

