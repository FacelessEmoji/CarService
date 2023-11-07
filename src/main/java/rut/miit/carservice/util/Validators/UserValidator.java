package rut.miit.carservice.util.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rut.miit.carservice.repositories.UserRepository;
import rut.miit.carservice.services.dtos.input.UserDTO;

/**
 * todo Document type UserValidator
 */
public class UserValidator implements Validator {

    public final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDto = (UserDTO) target;

        // Проверяем, что username не может быть “all”
        if ("all".equals(userDto.getUsername())) {
            errors.rejectValue("username", "Username.all", "Username can't be 'all'");
        }

        // Проверяем, что username по длине от 3 до 20 символов
        if (userDto.getUsername().length() < 3 || userDto.getUsername().length() > 20) {
            errors.rejectValue("username", "Username.length", "Username must be from 3 to 20 characters");
        }

        // Проверяем, что username уникален
        // (в реальной реализации необходимо использовать базу данных)
//        if (userRepository.existsByUsername(userDto.getUsername())) {
//            errors.rejectValue("username", "Username.unique", "Username must be unique");
//        }

        // Проверяем, что Имя и Фамилия начинаются с большой буквы
        if (!Character.isUpperCase(userDto.getFirstName().charAt(0))) {
            errors.rejectValue("firstName", "FirstName.startWithUpperCase", "First name must start with upper case");
        }

        if (!Character.isUpperCase(userDto.getLastName().charAt(0))) {
            errors.rejectValue("lastName", "LastName.startWithUpperCase", "Last name must start with upper case");
        }

        // Проверяем, что имя и фамилия содержат только буквы и пробелы
        if (!userDto.getFirstName().matches("[a-zA-Z\\s]+")) {
            errors.rejectValue("firstName", "FirstName.onlyLettersAndSpaces", "First name must contain only letters and spaces");
        }

        if (!userDto.getLastName().matches("[a-zA-Z\\s]+")) {
            errors.rejectValue("lastName", "LastName.onlyLettersAndSpaces", "Last name must contain only letters and spaces");
        }

        // Проверяем, что пароль содержит хотя бы одну цифру и одну букву
        if (!userDto.getPassword().matches(".*\\d.*") || !userDto.getPassword().matches(".*[a-zA-Z].*")) {
            errors.rejectValue("password", "Password.digitsAndLetters", "Password must contain at least one digit and one letter");
        }
    }
}