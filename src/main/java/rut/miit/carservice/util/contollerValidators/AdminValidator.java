package rut.miit.carservice.util.contollerValidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.implementations.UserServiceImpl;

/**
 * todo Document type AdminValidator
 */
@Component
public class AdminValidator implements Validator {
    private final UserServiceImpl userService;

    @Autowired
    public AdminValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> someClass) {
        return UserDTO.class.equals(someClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDto = (UserDTO) target;

        // Проверяем, что username уникален
        if (userService.getUserByUsername(userDto.getUsername()) != null) {
            errors.rejectValue("username", "Username.unique", "Username must be unique");
        }
        // Проверяем, что username не может быть “all”
        if ("all".equals(userDto.getUsername())) {
            errors.rejectValue("username", "Username.all", "Username can't be 'all'");
        }
    }
}