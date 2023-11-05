package rut.miit.carservice.services.interfaces.internalAPI;

import rut.miit.carservice.models.entities.User;

/**
 * todo Document type UserInternalService
 */
public interface UserInternalService<ID> {
    User getUserById(ID userId);
    void deleteUserById(ID userId);
}
