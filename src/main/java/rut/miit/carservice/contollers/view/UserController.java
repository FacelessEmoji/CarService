package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.implementations.UserServiceImpl;

//todo custom validators
@Controller
public class UserController {

    private UserServiceImpl userService;

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

    @GetMapping("/sign/in")
    public String signIn() {
        return "users/user-sign-in";
    }

    @PostMapping("/sign/in")
    public String signIn(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        UserDTO foundUser = userService.getBaseUserByUsername(userDTO.getUsername());
        System.out.println(foundUser);
        if (foundUser != null && foundUser.getPassword().equals(userDTO.getPassword())) {
            model.addAttribute("authSuccess", true);
        } else {
            model.addAttribute("authError", "Invalid username or password.");
        }
        return "users/user-sign-in";
    }

    @GetMapping("/sign/up")
    public String signUp() {
        return "users/user-sign-up";
    }

    //todo pass validation and check adding
    @PostMapping("/sign/up")
    public String signIn(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // Исправленные имена атрибутов для соответствия DTO
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            return "redirect:/sign/up";
        }
        userService.addNewUser(userDTO);
        return "redirect:/";
    }

    @GetMapping("/users/all")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users/user-all";
    }
}
