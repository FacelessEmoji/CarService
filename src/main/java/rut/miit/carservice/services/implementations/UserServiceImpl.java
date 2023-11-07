package rut.miit.carservice.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.models.entities.User;
import rut.miit.carservice.repositories.UserRepository;
import rut.miit.carservice.services.dtos.output.UserOutputDTO;
import rut.miit.carservice.services.interfaces.internalAPI.UserInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.UserService;
import rut.miit.carservice.util.ValidationUtilImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<String>, UserInternalService<String> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserOutputDTO getUserByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserOutputDTO.class);
    }

    @Override
    public List<UserOutputDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserOutputDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO addNewUser(UserDTO user) {
        if (!this.validationUtil.isValid(user)) {
            this.validationUtil
                .violations(user)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(System.out::println);
        } else {
            try {
                return modelMapper.map(userRepository.saveAndFlush(modelMapper.map(user, User.class)), UserDTO.class);
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }
        return null;
    }

    @Override
    public UserDTO updateUsername(String userId, String username) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setUsername(username);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updatePassword(String userId, String password) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setPassword(password);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateIsActive(String userId, boolean isActive) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setActive(isActive);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUserImageUrl(String userId, String imageUrl) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setImageUrl(imageUrl);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }
}

