package rut.miit.carservice.dtos.input;

import lombok.*;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.dtos.base.BaseDTO;
import rut.miit.carservice.dtos.base.TimestampedDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRoleDTO extends BaseDTO{
    private UserRoleType role;
}
