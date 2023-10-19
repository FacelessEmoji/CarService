package rut.miit.carservice.services;

import rut.miit.carservice.dtos.input.UserRoleDTO;
import rut.miit.carservice.models.enums.UserRoleType;

import java.util.List;

public interface UserRoleService<ID>{
    UserRoleDTO getRoleById(ID roleId);
    UserRoleDTO getRoleByName(UserRoleType roleName);
    List<UserRoleDTO> getAllRoles();
    UserRoleDTO addNewRole(UserRoleDTO roleDTO);
    UserRoleDTO updateRoleName(ID roleId,
                               UserRoleType roleName);
    void deleteRoleById(ID roleId);
}

