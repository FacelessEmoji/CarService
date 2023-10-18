package rut.miit.carservice.dtos.output;


import lombok.Data;
import rut.miit.carservice.dtos.input.UserRoleDTO;
import rut.miit.carservice.dtos.base.BaseDTO;
import rut.miit.carservice.dtos.base.TimestampedDTO;

@Data
public class UserOutputDTO extends BaseDTO{
    private String username;
    //pass
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String imageUrl;
    //
    private String role;
}
