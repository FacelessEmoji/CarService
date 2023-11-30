package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.CarModelServiceImpl;

/**
 * todo Document type ModelController
 */
@Controller
@RequestMapping("/models")
public class ModelController {
    private CarModelServiceImpl carModelService;

    @Autowired
    public void setCarModelService(CarModelServiceImpl carModelService) {
        this.carModelService = carModelService;
    }

    @ModelAttribute("modelDTO")
    public CarModelDTO initModel() {
        return new CarModelDTO();
    }

    @GetMapping("/add")
    public String addModel() {
        return "models/model-add";
    }
//
//    @PostMapping("/add")
//    public String addBrand(@Valid CarBrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            // Исправленные имена атрибутов для соответствия DTO
//            redirectAttributes.addFlashAttribute("brandDTO", brandDTO);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDTO",
//                bindingResult);
//            return "redirect:/brands/add";
//        }
//        brandService.addNewBrandDTO(brandDTO); // Убедитесь, что у вашего сервиса правильный метод
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/edit/{name}")
//    public String editBrand(@PathVariable String name, Model model) {
//        model.addAttribute("brandDTO", brandService.getBrandByName(name));
//        return "brands/brand-edit";
//    }
//
//    @PostMapping("/edit/{name}")
//    public String editBrand(@Valid CarBrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        System.out.println(brandService.getBrandById(brandDTO.getId()));
//        System.out.println("-----------------");
//        System.out.println(brandService.updateBrandNameByID(brandDTO.getId(), brandDTO.getName()));
//        if (bindingResult.hasErrors()) {
//            // Исправленные имена атрибутов для соответствия DTO
//            redirectAttributes.addFlashAttribute("brandDTO", brandDTO);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDTO",
//                bindingResult);
//            return "redirect:/brands/edit/" + brandDTO.getName();
//        }
//        //        brandService.updateBrandNameByID(brandDTO.getId(), brandDTO.getName()); // Убедитесь, что у вашего сервиса правильный метод
//        return "redirect:/brands/all";
//    }

    @GetMapping("/all")
    public String showAllModels(Model model) {
        model.addAttribute("allModels", carModelService.getAllModels());
        return "models/model-all";
    }
}
