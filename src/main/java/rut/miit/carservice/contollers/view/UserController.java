package rut.miit.carservice.contollers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.implementations.UserServiceImpl;

@Controller
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("userDTO")
    public UserDTO initUser() {
        return new UserDTO();
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
    public String signIn(@ModelAttribute("userDTO") UserDTO userDTO, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // Исправленные имена атрибутов для соответствия DTO
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            return "redirect://sign/up";
        }
        userDTO.setIsActive(Boolean.TRUE);
        userService.addNewUser(userService.setUser(userDTO));
        return "redirect:/";
    }

    @GetMapping("/users/all")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users/user-all";
    }
}
