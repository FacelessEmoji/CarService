package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.input.OfferDTO;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.CarModelServiceImpl;
import rut.miit.carservice.services.implementations.OfferServiceImpl;
import rut.miit.carservice.services.implementations.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private OfferServiceImpl offerService;
    private UserServiceImpl userService;
    private CarModelServiceImpl modelService;
    private CarBrandServiceImpl brandService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelService(CarModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setBrandService(CarBrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @ModelAttribute("offerDTO")
    public OfferDTO initOffer() {
        OfferDTO offerDTO = new OfferDTO();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO user = userService.getBaseUserByUsername(username);
        if (user != null) {
            offerDTO.setSeller(user.getId());
        }
        return offerDTO;
    }

    @GetMapping("/add")
    public String addOffer(Model model, Principal principal) {
        model.addAttribute("models", modelService.getAllModels());
        String username = principal != null ? principal.getName() : "N/A";
        model.addAttribute("username", username);
        return "offers/offer-add";
    }


    @PostMapping("/add")
    public String addOffer(@Valid OfferDTO offerDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("models", modelService.getAllModels());
            return "offers/offer-add";
        }
        offerService.addNewOffer(offerDTO);
        return "redirect:/offers/all";
    }

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable("id") String id, Model model) {
        model.addAttribute("offerDTO", offerService.getOfferById(id));
        model.addAttribute("models", modelService.getAllModels());

        // Добавление имени пользователя в модель
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "offers/offer-edit";
    }


    @PostMapping("/edit/{id}")
    public String editOffer(@PathVariable("id") String id, @Valid OfferDTO offerDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("offerDTO", offerDTO);
            model.addAttribute("org.springframework.validation.BindingResult.offerDTO", bindingResult);
            model.addAttribute("models", modelService.getAllModels());
            return "offers/offer-edit";
        }
        offerService.updateOffer(id, offerDTO);
        return "redirect:/offers/all";
    }

    @GetMapping("/all")
    public String showAllOffers(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("username", username);
        model.addAttribute("allOffers", offerService.getAllOffers());
        return "offers/offer-all";
    }
}
