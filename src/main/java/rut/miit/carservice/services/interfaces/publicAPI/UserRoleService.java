package rut.miit.carservice.services.interfaces.publicAPI;

import rut.miit.carservice.services.dtos.input.UserRoleDTO;
import rut.miit.carservice.models.enums.UserRoleType;

import java.util.List;

public interface UserRoleService<ID>{

    UserRoleDTO getRoleByName(UserRoleType roleName);
    List<UserRoleDTO> getAllRoles();
    UserRoleDTO addNewRole(UserRoleDTO roleDTO);
    UserRoleDTO updateRoleName(ID roleId,
                               UserRoleType roleName);

}

