package rut.miit.carservice.dtos.input;

import lombok.Data;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.dtos.base.BaseDTO;
import rut.miit.carservice.dtos.base.TimestampedDTO;

@Data
public class UserRoleDTO extends BaseDTO{
    private UserRoleType role;
}
