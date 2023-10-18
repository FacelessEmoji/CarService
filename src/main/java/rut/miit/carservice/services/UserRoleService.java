package rut.miit.carservice.services;

import rut.miit.carservice.dtos.input.UserRoleDTO;

import java.util.List;

public interface UserRoleService<ID>{
    UserRoleDTO getRoleById(ID roleId);
    List<UserRoleDTO> getAllRoles();
    UserRoleDTO addNewRole(UserRoleDTO roleDTO);
    UserRoleDTO updateRoleName(ID roleId, String roleName);
    void deleteRoleById(ID roleId);
}

