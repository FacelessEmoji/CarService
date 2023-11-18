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

    @Autowired
    public HomeController(CarModelServiceImpl modelService, CarBrandServiceImpl brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Получаем списки популярных брендов и моделей и добавляем их в модель
        model.addAttribute("popularBrands", brandService.getAllBrands());
        model.addAttribute("popularModels", modelService.getAllModels());
        return "home"; // Возвращает имя шаблона главной страницы
    }
}

