package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;

/**
 * todo Document type BrandController
 */
@Controller
@RequestMapping("/brands")
public class BrandController {

    private CarBrandServiceImpl brandService;

    @Autowired
    public void setBrandService(CarBrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addBrand() {
        return "brands/brand-add";
    }

    @ModelAttribute("brandDTO")
    public CarBrandDTO initBrand() {
        return new CarBrandDTO();
    }

    @PostMapping("/add")
    public String addBrand(@Valid CarBrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // Исправленные имена атрибутов для соответствия DTO
            redirectAttributes.addFlashAttribute("brandDTO", brandDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDTO",
                bindingResult);
            return "redirect:/brands/add";
        }
        brandService.addNewBrandDTO(brandDTO); // Убедитесь, что у вашего сервиса правильный метод

        return "redirect:/";
    }


    @GetMapping("/all")
    public String showAllBrands(Model model) {
        //model.addAttribute("companyInfos", companyService.allCompanies());
        model.addAttribute("allBrands", brandService.getAllBrands());
        return "brands/brand-all";
    }
}
