package rut.miit.carservice.contollers;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.dtos.output.UserOutputDTO;
import rut.miit.carservice.services.implementations.UserServiceImpl;
import rut.miit.carservice.services.interfaces.publicAPI.UserService;
import rut.miit.carservice.util.contollerValidators.AdminValidator;
import rut.miit.carservice.util.contollerValidators.UserValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * todo add admin validation
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService<String> userService;

    @Autowired
    public UserController(UserService<String> userService) {
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator((UserServiceImpl) userService));
    }

    @GetMapping("/find/all")
    public List<UserOutputDTO> findAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/find/{username}")
    public UserOutputDTO findByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/add/user")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(userService.addNewUser(userService.setUser(userDTO)));
    }

    @PostMapping("/add/admin")
    public ResponseEntity<?> addAdmin(@Valid @RequestBody UserDTO adminDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(userService.addNewUser(userService.setAdmin(adminDTO)));
    }

    @PutMapping("/update/{userId}")
    public UserDTO updateUsername(@PathVariable String userId, @RequestParam String newUsername) {
        return userService.updateUsername(userId, newUsername);
    }

    @PutMapping("/update/password/{userId}")
    public UserDTO updatePassword(@PathVariable String userId, @RequestParam String newPassword) {
        return userService.updatePassword(userId, newPassword);
    }

    @PutMapping("/update/isActive/{userId}")
    public UserDTO updateIsActive(@PathVariable String userId, @RequestParam boolean newIsActive) {
        return userService.updateIsActive(userId, newIsActive);
    }

    @PutMapping("/update/imageUrl/{userId}")
    public UserDTO updateUserImageUrl(@PathVariable String userId, @RequestParam String newImageUrl) {
        return userService.updateUserImageUrl(userId, newImageUrl);
    }
}
