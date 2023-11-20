package rut.miit.carservice.contollers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.CarModelServiceImpl;
import rut.miit.carservice.services.implementations.OfferServiceImpl;

/**
 * todo Document type HomeController
 */
@Controller
public class HomeController {

    private final CarModelServiceImpl modelService;
    private final CarBrandServiceImpl brandService;
    private final OfferServiceImpl offerService; // Добавлен сервис для работы с предложениями

    @Autowired
    public HomeController(CarModelServiceImpl modelService, CarBrandServiceImpl brandService, OfferServiceImpl offerService) {
        this.modelService = modelService;
        this.brandService = brandService;
        this.offerService = offerService; // Инициализация сервиса предложений
    }

    @GetMapping("/")
    public String home(Model model) {
        // Получаем списки популярных брендов, моделей и всех предложений и добавляем их в модель
        model.addAttribute("popularBrands", brandService.getAllBrands());
        model.addAttribute("popularModels", modelService.getAllModels());
        model.addAttribute("offers", offerService.getAllOffers()); // Добавляем предложения в модель
        return "main/home"; // Возвращает имя шаблона главной страницы
    }
}


