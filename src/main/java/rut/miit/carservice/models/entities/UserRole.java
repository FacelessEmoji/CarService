package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import lombok.*;

import rut.miit.carservice.models.baseEntities.BaseEntity;
import rut.miit.carservice.models.enums.UserRoleType;

import java.util.*;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
public class UserRole extends BaseEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", length = 11, nullable = false)
    private UserRoleType role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();
}
