package rut.miit.carservice.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.models.entities.User;
import rut.miit.carservice.repositories.UserRepository;
import rut.miit.carservice.services.interfaces.internalAPI.UserInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.UserService;
import rut.miit.carservice.util.ValidationUtilImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UUID>, UserInternalService<UUID> {
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
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO addNewUser(UserDTO userDTO) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public UserDTO updateUsername(UUID userId, String username) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setUsername(username);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updatePassword(UUID userId, String password) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setPassword(password);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateIsActive(UUID userId, boolean isActive) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setActive(isActive);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUserImageUrl(UUID userId, String imageUrl) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setImageUrl(imageUrl);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }
}

