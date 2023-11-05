package rut.miit.carservice.services.dtos.output;


import lombok.*;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
