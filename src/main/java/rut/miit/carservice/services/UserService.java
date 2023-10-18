package rut.miit.carservice.services;

import rut.miit.carservice.dtos.input.UserDTO;

import java.util.List;

public interface UserService<ID>{
    UserDTO getUserById(ID userId);
    List<UserDTO> getAllUsers();
    UserDTO addNewUser(UserDTO userDTO);
    UserDTO updateUsername(ID userId, String username);
    UserDTO updatePassword(ID userId, String password);
    UserDTO updateIsActive(ID userId, boolean isActive);
    UserDTO updateUserImageUrl(ID userId, String imageUrl);
    void deleteUserById(ID userId);
}

