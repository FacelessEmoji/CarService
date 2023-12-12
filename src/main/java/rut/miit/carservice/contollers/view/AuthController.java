package rut.miit.carservice.contollers.view;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.implementations.UserServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/")
public class AuthController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
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

    @GetMapping("/sign/up")
    public String signUp() {
        LOG.log(Level.INFO, "Accessed sign up page.");
        return "auth/user-sign-up";
    }


    @PostMapping("/sign/up")
    public String signIn(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            LOG.log(Level.WARN, "Failed sign up attempt with errors at " + currentTime + ".");
            return "redirect:/sign/up";
        }
        userService.addNewUser(userDTO);
        LOG.log(Level.INFO, "New user registered: " + userDTO.getUsername() + " at " + currentTime + ".");
        return "redirect:/";
    }



    @GetMapping("/sign/in")
    public String signIn() {
        LOG.log(Level.INFO, "Accessed sign in page.");
        return "auth/user-sign-in";
    }



    @PostMapping("/sign/in/error")
    public String onFailedLogin(
        @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
        RedirectAttributes redirectAttributes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);
        LOG.log(Level.WARN, "Failed sign in attempt for username: " + username + " at " + currentTime + ".");
        return "redirect:/sign/in";
    }


    @GetMapping("/forbidden")
    public String forbidden() {
        LOG.log(Level.INFO, "Accessed forbidden page.");
        return "auth/forbidden";
    }

}