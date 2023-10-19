package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import lombok.*;

import rut.miit.carservice.models.baseEntities.BaseEntity;
import rut.miit.carservice.models.converters.RoleTypeConverter;
import rut.miit.carservice.models.enums.UserRoleType;

import java.util.*;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
public class UserRole extends BaseEntity {

    @Convert(converter = RoleTypeConverter.class)
    @Column(name = "role", length = 11, nullable = false)
    private UserRoleType role;

    @OneToMany(mappedBy = "role", orphanRemoval = true)
    private List<User> users = new ArrayList<>();
}
