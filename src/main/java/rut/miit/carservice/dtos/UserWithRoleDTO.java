package rut.miit.carservice.dtos;

import lombok.Data;
import rut.miit.carservice.models.enums.UserRoleType;

import java.util.UUID;

@Data
public class UserWithRoleDTO {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String imageUrl;
    //UserRole
    private String userRoleName;
}
