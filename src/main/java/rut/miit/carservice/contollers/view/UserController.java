package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;

/**
 * todo Document type UserController
 */
public class UserController {

    private CarBrandServiceImpl brandService;

    @Autowired
    public void setBrandService(CarBrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @ModelAttribute("brandDTO")
    public CarBrandDTO initBrand() {
        return new CarBrandDTO();
    }

    @GetMapping("/add")
    public String addBrand() {
        return "brands/brand-add";
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

    @GetMapping("/edit/{name}")
    public String editBrand(@PathVariable String name, Model model) {
        model.addAttribute("brandDTO", brandService.getBrandByName(name));
        return "brands/brand-edit";
    }

    @PostMapping("/edit/{name}")
    public String editBrand(@Valid CarBrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println(brandService.getBrandById(brandDTO.getId()));
        System.out.println("-----------------");
        System.out.println(brandService.updateBrandNameByID(brandDTO.getId(), brandDTO.getName()));
        if (bindingResult.hasErrors()) {
            // Исправленные имена атрибутов для соответствия DTO
            redirectAttributes.addFlashAttribute("brandDTO", brandDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDTO",
                bindingResult);
            return "redirect:/brands/edit/" + brandDTO.getName();
        }
        //        brandService.updateBrandNameByID(brandDTO.getId(), brandDTO.getName()); // Убедитесь, что у вашего сервиса правильный метод
        return "redirect:/brands/all";
    }

    @GetMapping("/all")
    public String showAllBrands(Model model) {
        model.addAttribute("allBrands", brandService.getAllBrands());
        return "brands/brand-all";
    }
}
