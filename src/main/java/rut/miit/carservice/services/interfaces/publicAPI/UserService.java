package rut.miit.carservice.services.interfaces.publicAPI;

import rut.miit.carservice.services.dtos.input.UserDTO;
import rut.miit.carservice.services.dtos.output.UserOutputDTO;

import java.util.List;

public interface UserService<ID>{
    UserOutputDTO getUserByUsername(String username);
    UserDTO getBaseUserByUsername(String username);
    List<UserOutputDTO> getAllUsers();
    UserDTO addNewUser(UserDTO userDTO);
    UserDTO updateUser(ID userId, UserDTO userDTO);
    UserDTO updateUsername(ID userId, String username);
    UserDTO updatePassword(ID userId, String password);
    UserDTO updateIsActive(ID userId, boolean isActive);
    UserDTO updateUserImageUrl(ID userId, String imageUrl);
    UserDTO setAdmin(UserDTO userDTO);
    UserDTO setUser(UserDTO userDTO);
}

