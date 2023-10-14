package rut.miit.carservice.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rut.miit.carservice.repositories.*;
import rut.miit.carservice.models.entities.*;
import rut.miit.carservice.models.enums.*;

import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional
    public void createData() {
        CarBrand brand1 = new CarBrand("Toyota");
        carBrandRepository.save(brand1);
        CarBrand brand2 = new CarBrand("BMW");
        carBrandRepository.save(brand2);

        CarModel model1 = new CarModel("Camry", ModelCategory.CAR, "imageUrl1", 1990, 2021, brand1);
        carModelRepository.save(model1);
        CarModel model2 = new CarModel("X5", ModelCategory.TRUCK, "imageUrl2", 2000, 2021, brand2);
        carModelRepository.save(model2);
        CarModel model3 = new CarModel("Corolla", ModelCategory.CAR, "imageUrl3", 1995, 2021, brand1);
        carModelRepository.save(model3);

        UserRole userRole = new UserRole(UserRoleType.USER);
        userRoleRepository.save(userRole);
        UserRole adminRole = new UserRole(UserRoleType.ADMIN);
        userRoleRepository.save(adminRole);

        User user1 = new User("username1", "password1", "John", "Doe", true, "userImageUrl1", userRole);
        userRepository.save(user1);
        User user2 = new User("username2", "password2", "Jane", "Doe", true, "userImageUrl2", userRole );
        userRepository.save(user2);

        User admin = new User("admin", "adminPass", "Admin", "Admin", true, "adminImageUrl", adminRole);
        userRepository.save(admin);
        User admin2 = new User("admin2", "adminPass2", "Super", "Admin", true, "adminImageUrl2", adminRole);
        userRepository.save(admin2);

        Offer offer1 = new Offer("Description1", EngineType.GASOLINE, "offerImageUrl1", 50000, new BigDecimal("10000.00"), TransmissionType.AUTOMATIC, 2018, model1, user1);
        offerRepository.save(offer1);
        Offer offer2 = new Offer("Description2", EngineType.DIESEL, "offerImageUrl2", 60000, new BigDecimal("15000.00"), TransmissionType.MANUAL, 2019, model2, user2);
        offerRepository.save(offer2);
        Offer offer3 = new Offer("Description3", EngineType.ELECTRIC, "offerImageUrl3", 10000, new BigDecimal("25000.00"), TransmissionType.AUTOMATIC, 2020, model3, admin);
        offerRepository.save(offer3);
    }

    @Override
    public void run(String... args) throws Exception {
        createData();

//        // Создаем предложения
//        Offer offer1 = new Offer("Description1", EngineType.GASOLINE, "offerImageUrl1", 50000, new BigDecimal("10000.00"), TransmissionType.AUTOMATIC, 2018, model1, user1);
//        offerRepository.save(offer1);
//        // Добавляем еще одного пользователя и админа
//        // Создаем еще предложения
//        Offer offer2 = new Offer("Description2", EngineType.DIESEL, "offerImageUrl2", 60000, new BigDecimal("15000.00"), TransmissionType.MANUAL, 2019, model2, user2);
//        offerRepository.save(offer2);
//
//        Offer offer3 = new Offer("Description3", EngineType.ELECTRIC, "offerImageUrl3", 10000, new BigDecimal("25000.00"), TransmissionType.AUTOMATIC, 2020, model3, admin);
//        offerRepository.save(offer3);
    }
}

