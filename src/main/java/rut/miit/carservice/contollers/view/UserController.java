package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class UserController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
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

    @GetMapping("users/edit/{id}")
    public String editModel(@PathVariable("id") String id, Model model, Principal principal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);
        String logMessage = String.format("User %s started editing profile with ID %s at %s.", principal.getName(), id, currentTime);
        LOG.log(Level.INFO, logMessage);

        User user = userService.getUserById(id);
        user.setPassword("Testing1");
        model.addAttribute("userDTO", user);
        return "users/user-edit";
    }

    @PostMapping("users/edit/{id}")
    public String editModel(@PathVariable String id, @Valid UserDTO userDTO, BindingResult bindingResult, Model model, Authentication currentAuthentication,
        Principal principal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);
        String startLogMessage = String.format("User %s started POST request to edit profile with ID %s at %s.", principal.getName(), id, currentTime);
        LOG.log(Level.INFO, startLogMessage);

        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            model.addAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            return "users/user-edit";
        }

        userService.updateUser(id, userDTO);
        UserDetails newUserDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
        Authentication newAuthentication =
            new UsernamePasswordAuthenticationToken(newUserDetails, currentAuthentication.getCredentials(), newUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        String endLogMessage = String.format("User %s successfully updated profile with ID %s at %s.", principal.getName(), id, currentTime);
        LOG.log(Level.INFO, endLogMessage);

        return "redirect:/users/profile";
    }

    @GetMapping("/users/profile")
    public String profile(Principal principal, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);
        String logMessage = String.format("User %s opened his profile at %s", principal.getName(), currentTime);
        LOG.log(Level.INFO, logMessage);

        String username = principal.getName();
        model.addAttribute("user", userService.getUserByUsername(username));

        List<OfferWithDetailsDTO> userOffers = offerService.getOffersBySellerUsername(username);
        System.out.println(userOffers);
        model.addAttribute("userOffers", userOffers);
        model.addAttribute("hasOffers", !userOffers.isEmpty());

        return "users/user-profile";
    }

    @GetMapping("/users/all")
    public String showAllUsers(Model model, Principal principal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);
        String logMessage = String.format("User %s accessed all users list at %s.", principal.getName(), currentTime);
        LOG.log(Level.INFO, logMessage);

        model.addAttribute("allUsers", userService.getAllUsers());
        return "users/user-all";
    }
}
