package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    private CarBrandServiceImpl brandService;

    @Autowired
    public void setCarModelService(CarModelServiceImpl carModelService) {
        this.carModelService = carModelService;
    }

    @Autowired
    public void setBrandService(CarBrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @ModelAttribute("modelDTO")
    public CarModelDTO initModel() {
        return new CarModelDTO();
    }

    @GetMapping("/add")
    public String addModel(Model model){
        model.addAttribute("brands", brandService.getAllBrands());
        return "models/model-add";
    }

    @PostMapping("/add")
    public String addModel(@Valid CarModelDTO modelDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelDTO", modelDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
            return "redirect:/models/add";
        }
        carModelService.addNewModel(modelDTO);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editModel(@PathVariable("id")  String id, Model model) {
        model.addAttribute("modelDTO", carModelService.getModelById(id));
        model.addAttribute("brands", brandService.getAllBrands());
        return "models/model-edit";
    }

    @PostMapping("/edit/{id}")
    public String editModel(@PathVariable String id, @Valid CarModelDTO modelDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("modelDTO", modelDTO);
            model.addAttribute("org.springframework.validation.BindingResult.modelDTO", bindingResult);
            model.addAttribute("brands", brandService.getAllBrands()); // Добавьте список брендов
            return "models/model-edit";
        }
        carModelService.updateModel(id, modelDTO);
        return "redirect:/models/all";
    }


    @GetMapping("/all")
    public String showAllModels(Model model) {
        model.addAttribute("allModels", carModelService.getAllModels());
        return "models/model-all";
    }
}
