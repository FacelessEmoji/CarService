package rut.miit.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.carservice.models.entities.UserRole;
import rut.miit.carservice.models.enums.UserRoleType;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    UserRole findByRole(UserRoleType userRoleType);
}
