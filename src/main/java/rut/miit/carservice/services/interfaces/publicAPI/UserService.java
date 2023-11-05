package rut.miit.carservice.services.interfaces.publicAPI;

import rut.miit.carservice.services.dtos.input.UserDTO;

import java.util.List;

public interface UserService<ID>{
    UserDTO getUserByUsername(String username);
    List<UserDTO> getAllUsers();
    UserDTO addNewUser(UserDTO userDTO);
    UserDTO updateUsername(ID userId, String username);
    UserDTO updatePassword(ID userId, String password);
    UserDTO updateIsActive(ID userId, boolean isActive);
    UserDTO updateUserImageUrl(ID userId, String imageUrl);

}

