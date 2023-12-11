package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.models.entities.User;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.dtos.output.OfferWithDetailsDTO;
import rut.miit.carservice.services.dtos.output.UserOutputDTO;
import rut.miit.carservice.services.implementations.OfferServiceImpl;
import rut.miit.carservice.services.implementations.UserServiceImpl;
import rut.miit.carservice.services.security.AppUserDetailsService;

import java.security.Principal;
import java.util.List;

//todo custom validators
@Controller
public class UserController {
    private AppUserDetailsService userDetailsService;
    private UserServiceImpl userService;
    private OfferServiceImpl offerService;

    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @Autowired
    public void setUserDetailsService(AppUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("userDTO")
    public UserDTO initUser() {
        UserDTO user = new UserDTO();
        user.setIsActive(Boolean.TRUE);
        userService.setUser(user);
        return user;
    }

    @ModelAttribute("adminDTO")
    public UserDTO initAdmin() {
        UserDTO user = new UserDTO();
        user.setIsActive(Boolean.TRUE);
        userService.setAdmin(user);
        return user;
    }
//todo fix endpoints for sign in sign up

    @GetMapping("users/edit/{id}")
    public String editModel(@PathVariable("id")  String id, Model model) {
        User user = userService.getUserById(id);
        user.setPassword("Testing1");
        model.addAttribute("userDTO", user);
        return "users/user-edit";
    }

    @PostMapping("users/edit/{id}")
    public String editModel(@PathVariable String id, @Valid UserDTO userDTO, BindingResult bindingResult, Model model, Authentication currentAuthentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            model.addAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            return "users/user-edit";
        }

        userService.updateUser(id, userDTO);

        // Получение новых данных пользователя
        UserDetails newUserDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());

        // Обновление Authentication объекта
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(newUserDetails, currentAuthentication.getCredentials(), newUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        return "redirect:/users/profile";
    }


    @GetMapping("/users/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userService.getUserByUsername(username));

        List<OfferWithDetailsDTO> userOffers = offerService.getOffersBySellerUsername(username);
        System.out.println(userOffers);
        model.addAttribute("userOffers", userOffers);
        model.addAttribute("hasOffers", !userOffers.isEmpty());

        return "users/user-profile";
    }


    @GetMapping("/users/all")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users/user-all";
    }
}
