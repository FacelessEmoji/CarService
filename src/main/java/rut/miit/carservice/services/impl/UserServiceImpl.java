package rut.miit.carservice.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.dtos.input.UserDTO;
import rut.miit.carservice.models.entities.User;
import rut.miit.carservice.repositories.UserRepository;
import rut.miit.carservice.services.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UUID> {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO getUserById(UUID userId) {
        return modelMapper.map(userRepository.findById(userId).orElseThrow(), UserDTO.class);
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
        user.setIsActive(isActive);
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

