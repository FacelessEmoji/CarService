package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;

import java.security.Principal;

/**
 * todo Document type BrandController
 */
@Controller
@RequestMapping("/brands")
public class BrandController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
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
    public String addBrand(Principal principal) {
        LOG.log(Level.INFO, "User " + principal.getName() + " accessed add brand page.");
        return "brands/brand-add";
    }

    @PostMapping("/add")
    public String addBrand(@Valid CarBrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandDTO", brandDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDTO", bindingResult);
            LOG.log(Level.WARN, "User " + principal.getName() + " attempted to add a brand with errors.");
            return "redirect:/brands/add";
        }
        brandService.addNewBrandDTO(brandDTO);
        LOG.log(Level.INFO, "User " + principal.getName() + " added a new brand: " + brandDTO.getName());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editBrand(@PathVariable("id") String id, Model model, Principal principal) {
        LOG.log(Level.INFO, "User " + principal.getName() + " started editing brand with ID " + id);
        model.addAttribute("brandDTO", brandService.getBrandById(id));
        return "brands/brand-edit";
    }

    @PostMapping("/edit/{id}")
    public String editBrand(@PathVariable String id, @Valid CarBrandDTO brandDTO, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brandDTO", brandDTO);
            model.addAttribute("org.springframework.validation.BindingResult.brandDTO", bindingResult);
            LOG.log(Level.WARN, "User " + principal.getName() + " attempted to edit a brand with ID " + id + " with errors.");
            return "brands/brand-edit";
        }
        brandService.updateBrandNameByID(id, brandDTO.getName());
        LOG.log(Level.INFO, "User " + principal.getName() + " updated brand with ID " + id);
        return "redirect:/brands/all";
    }

    @GetMapping("/all")
    public String showAllBrands(Model model, Principal principal) {
        LOG.log(Level.INFO, "User " + principal.getName() + " accessed all brands list.");
        model.addAttribute("allBrands", brandService.getAllBrands());
        return "brands/brand-all";
    }
}
