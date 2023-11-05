package rut.miit.carservice.services.interfaces.internalAPI;

import rut.miit.carservice.models.entities.UserRole;

/**
 * todo Document type UserRoleInternalService
 */
public interface UserRoleInternalService<ID> {
    UserRole getRoleById(ID roleId);
    void deleteRoleById(ID roleId);
}
