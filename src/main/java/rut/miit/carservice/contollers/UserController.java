package rut.miit.carservice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.dtos.output.UserOutputDTO;
import rut.miit.carservice.services.interfaces.publicAPI.UserService;

import java.util.List;

/**
 * todo add all to prohibited usernames, models and other
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService<String> userService;

    @Autowired
    public UserController(UserService<String> userService) {
        this.userService = userService;
    }

    @GetMapping("/find/all")
    public List<UserOutputDTO> findAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/find/{username}")
    public UserOutputDTO findByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping("/add")
    public UserDTO add(@RequestBody UserDTO userDTO){
        return userService.addNewUser(userDTO);
    }

    @PutMapping("/update/{userId}")
    public UserDTO updateUsername(@PathVariable String userId, @RequestParam String newUsername){
        return userService.updateUsername(userId, newUsername);
    }

    @PutMapping("/update/password/{userId}")
    public UserDTO updatePassword(@PathVariable String userId, @RequestParam String newPassword){
        return userService.updatePassword(userId, newPassword);
    }

    @PutMapping("/update/isActive/{userId}")
    public UserDTO updateIsActive(@PathVariable String userId, @RequestParam boolean newIsActive){
        return userService.updateIsActive(userId, newIsActive);
    }

    @PutMapping("/update/imageUrl/{userId}")
    public UserDTO updateUserImageUrl(@PathVariable String userId, @RequestParam String newImageUrl){
        return userService.updateUserImageUrl(userId, newImageUrl);
    }
}
