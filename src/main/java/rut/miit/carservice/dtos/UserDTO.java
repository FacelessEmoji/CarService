package rut.miit.carservice.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String imageUrl;
    private UserRoleDTO role;
}
