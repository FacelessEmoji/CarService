package rut.miit.carservice.dtos;

import lombok.Data;
import rut.miit.carservice.models.enums.UserRoleType;

import java.util.UUID;

@Data
public class UserRoleDTO {
    private UUID id;
    private UserRoleType role;
    // Если нужен полный список пользователей для этой роли:
    // private List<UserDTO> users;
}
