package rut.miit.carservice.contollers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rut.miit.carservice.services.dtos.output.OfferWithDetailsDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.CarModelServiceImpl;
import rut.miit.carservice.services.implementations.OfferServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * todo Document type HomeContollers
 */
@Controller
public class HomeController {

    private CarBrandServiceImpl brandService;

    private CarModelServiceImpl modelService;

    private OfferServiceImpl offerService;

    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @Autowired
    public void setBrandService(CarBrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setModelService(CarModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("brands", brandService.getAllBrands()); // Добавляем список брендов
        model.addAttribute("models", modelService.getAllModels()); // Добавляем список моделей
        return "home/index";
    }

    @GetMapping("/search")
    public String searchOffers(@RequestParam String brandName,
        @RequestParam String modelName,
        @RequestParam(defaultValue = "0") BigDecimal minPrice,
        @RequestParam(defaultValue = "10000000") BigDecimal maxPrice,
        Model model) {
        List<OfferWithDetailsDTO> offers = offerService.getOffersByPriceBetweenAndName(brandName, modelName, minPrice, maxPrice);
        model.addAttribute("offers", offers);
        return "home/search-results";
    }
}