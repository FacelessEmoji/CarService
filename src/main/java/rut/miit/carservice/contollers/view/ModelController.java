package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.CarModelServiceImpl;

import java.security.Principal;

/**
 * todo Document type ModelController
 */
@Controller
@RequestMapping("/models")
public class ModelController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
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
    public String addModel(Model model, Principal principal){
        LOG.log(Level.INFO, "User " + principal.getName() + " accessed add model page.");
        model.addAttribute("brands", brandService.getAllBrands());
        return "models/model-add";
    }

    @PostMapping("/add")
    public String addModel(@Valid CarModelDTO modelDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelDTO", modelDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
            LOG.log(Level.WARN, "User " + principal.getName() + " attempted to add a model with errors.");
            return "redirect:/models/add";
        }
        carModelService.addNewModel(modelDTO);
        LOG.log(Level.INFO, "User " + principal.getName() + " added a new model: " + modelDTO.getName());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editModel(@PathVariable("id")  String id, Model model, Principal principal) {
        LOG.log(Level.INFO, "User " + principal.getName() + " started editing model with ID " + id);
        model.addAttribute("modelDTO", carModelService.getModelById(id));
        model.addAttribute("brands", brandService.getAllBrands());
        return "models/model-edit";
    }

    @PostMapping("/edit/{id}")
    public String editModel(@PathVariable String id, @Valid CarModelDTO modelDTO, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("modelDTO", modelDTO);
            model.addAttribute("org.springframework.validation.BindingResult.modelDTO", bindingResult);
            model.addAttribute("brands", brandService.getAllBrands());
            LOG.log(Level.WARN, "User " + principal.getName() + " attempted to edit a model with ID " + id + " with errors.");
            return "models/model-edit";
        }
        carModelService.updateModel(id, modelDTO);
        LOG.log(Level.INFO, "User " + principal.getName() + " updated model with ID " + id);
        return "redirect:/models/all";
    }

    @GetMapping("/all")
    public String showAllModels(Model model, Principal principal) {
        LOG.log(Level.INFO, "User " + principal.getName() + " accessed all models list.");
        model.addAttribute("allModels", carModelService.getAllModels());
        return "models/model-all";
    }
}
