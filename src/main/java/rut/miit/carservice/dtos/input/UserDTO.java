package rut.miit.carservice.dtos.input;

import lombok.*;
import rut.miit.carservice.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
