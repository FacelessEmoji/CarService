package rut.miit.carservice.contollers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rut.miit.carservice.services.dtos.output.CarModelOutputDTO;
import rut.miit.carservice.services.dtos.output.OfferWithDetailsDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.CarModelServiceImpl;
import rut.miit.carservice.services.implementations.OfferServiceImpl;

import java.math.BigDecimal;
import java.util.List;

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

    @GetMapping("/get-models")
    @ResponseBody
    public List<CarModelOutputDTO> getModelsByBrand(@RequestParam String brandName) {
        return modelService.getAllModelsByBrand(brandName);
    }


    @GetMapping("/")
    public String homePage(
        @RequestParam(required = false) String brandName,
        @RequestParam(required = false) String modelName,
        @RequestParam(required = false) BigDecimal minPrice,
        @RequestParam(required = false) BigDecimal maxPrice,
        Model model) {

        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("models", modelService.getAllModels());

        // Установка значений по умолчанию
        if (minPrice == null) {
            minPrice = BigDecimal.ZERO;
        }
        if (maxPrice == null) {
            maxPrice = new BigDecimal("100000000");
        }

        // Проверка на "All"
        if ("All".equals(modelName)) {
            List<OfferWithDetailsDTO> offers = offerService.getOffersByPriceBetweenAndBrand(brandName, minPrice, maxPrice);
            model.addAttribute("offers", offers);
        } else if (brandName != null && modelName != null) {
            List<OfferWithDetailsDTO> offers = offerService.getOffersByPriceBetweenAndName(brandName, modelName, minPrice, maxPrice);
            model.addAttribute("offers", offers);
        }

        return "home/index";
    }
}