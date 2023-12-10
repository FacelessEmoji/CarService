package rut.miit.carservice.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.repositories.UserRoleRepository;
import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.models.entities.User;
import rut.miit.carservice.repositories.UserRepository;
import rut.miit.carservice.services.dtos.input.UserRoleDTO;
import rut.miit.carservice.services.dtos.output.UserOutputDTO;
import rut.miit.carservice.services.interfaces.internalAPI.UserInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//todo: check encrypt
@Service
public class UserServiceImpl implements UserService<String>, UserInternalService<String> {
    private UserRepository userRepository;
    private UserRoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setRoleRepository(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserOutputDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserOutputDTO.class);
    }

    @Override
    public UserDTO getBaseUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            System.out.println("User with this username not found");
            return null;
        }
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserOutputDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserOutputDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO addNewUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return modelMapper.map(userRepository.saveAndFlush(modelMapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public UserDTO setAdmin(UserDTO userDTO) {
        userDTO.setRole(modelMapper.map(roleRepository.findByRole(UserRoleType.ADMIN), UserRoleDTO.class));
        return userDTO;
    }

    @Override
    public UserDTO setUser(UserDTO userDTO) {
        userDTO.setRole(modelMapper.map(roleRepository.findByRole(UserRoleType.USER), UserRoleDTO.class));
        return userDTO;
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setImageUrl(userDTO.getImageUrl());
        return modelMapper.map(userRepository.saveAndFlush(user), UserDTO.class);
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

