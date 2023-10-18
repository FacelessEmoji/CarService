package rut.miit.carservice.dtos.input;

import lombok.Data;
import rut.miit.carservice.dtos.base.BaseDTO;

@Data
public class UserDTO extends BaseDTO{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String imageUrl;
    //
    private UserRoleDTO role;
}
