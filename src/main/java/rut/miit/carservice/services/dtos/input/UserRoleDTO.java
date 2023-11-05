package rut.miit.carservice.services.dtos.input;

import lombok.*;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRoleDTO extends BaseDTO{
    private UserRoleType role;
}
