package rut.miit.carservice.contollers.api;

import jakarta.validation.*;
import jakarta.validation.constraints.AssertFalse;
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
import java.util.Map;

/**
 * todo add admin validation
 */
@RestController
@RequestMapping("/api/user")
public class UserControllerAPI {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator(userService));
    }

    @GetMapping("/find")
    public ResponseEntity<?> findByUsername(
        @RequestParam(name = "username", defaultValue = "null") String username
    ) {
        if (username.equals("all")){
            return ResponseEntity.ok(userService.getAllUsers());
        } else if (username.equals("null")){
            return ResponseEntity.badRequest().body("Username can't be null");
        } else return ResponseEntity.ok(userService.getUserByUsername(username));
    }

//    @PostMapping("/add/test")
//    public ResponseEntity<?> add(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
//        if (result.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError fieldError : result.getFieldErrors()) {
//                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
//            }
//            return ResponseEntity.badRequest().body(errors);
//        }
//        return ResponseEntity.ok(userService.addNewUser(userDTO));
//    }

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

    @PutMapping("/update/username")
    public ResponseEntity<?> updateUsername(
        @RequestParam(name = "id", defaultValue = "null") String userId,
        @RequestParam(name = "username", defaultValue = "null") String newUsername
    ) {
        if (userId.equals("null") || newUsername.equals("null")){
            return ResponseEntity.badRequest().body("User id or new username can't be null");
        }else if (userService.getUserById(userId).getUsername().equals(newUsername)){
            return ResponseEntity.badRequest().body("New username can't be the same as the old one");
        } else return ResponseEntity.ok(userService.updateUsername(userId, newUsername));
    }

    @PutMapping("/update/password")
    public ResponseEntity<?> updatePassword(
        @RequestParam(name = "id", defaultValue = "null") String userId,
        @RequestParam(name = "password", defaultValue = "null") String newPassword) {
        if (userId.equals("null") || newPassword.equals("null")){
            return ResponseEntity.badRequest().body("User id or new password can't be null");
        } else if (userService.getUserById(userId).getPassword().equals(newPassword)){
            return ResponseEntity.badRequest().body("New password can't be the same as the old one");
        } else return ResponseEntity.ok(userService.updatePassword(userId, newPassword));
    }

    @PutMapping("/update/isActive")
    public ResponseEntity<?> updateIsActive(
        @RequestParam(name = "id", defaultValue = "null") String userId,
        @RequestParam(name = "isActive", defaultValue = "true") boolean newIsActive) {
        if (userId.equals("null")) {
            return ResponseEntity.badRequest().body("User id can't be null");
        } else if (userService.getUserById(userId).getActive().equals(newIsActive)){
            return ResponseEntity.badRequest().body("New user state can't be the same as the old one");
        } else return ResponseEntity.ok(userService.updateIsActive(userId, newIsActive));
    }

    @PutMapping("/update/imageUrl")
    public ResponseEntity<?> updateUserImageUrl(
        @RequestParam(name = "id", defaultValue = "null") String userId,
        @RequestParam(name = "imageUrl", defaultValue = "null") String newImageUrl) {
        if (userId.equals("null") || newImageUrl.equals("null")) {
            return ResponseEntity.badRequest().body("User id or new image url can't be null");
        } else if (userService.getUserById(userId).getImageUrl().equals(newImageUrl)){
            return ResponseEntity.badRequest().body("New image url can't be the same as the old one");
        } else return ResponseEntity.ok(userService.updateUserImageUrl(userId, newImageUrl));
    }
}
