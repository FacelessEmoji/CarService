package rut.miit.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.carservice.models.entities.UserRole;
import rut.miit.carservice.models.enums.UserRoleType;

import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    UserRole findByRole(UserRoleType userRoleType);
}
