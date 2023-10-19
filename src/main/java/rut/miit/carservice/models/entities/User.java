package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.DumperOptions;
import rut.miit.carservice.models.baseEntities.TimestampedEntity;

import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends TimestampedEntity {
    @Column(name = "username", length = 255, nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "firstName", length = 255, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 255, nullable = false)
    private String lastName;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "imageUrl", length = 512, nullable = false)
    private String imageUrl;

    //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private UserRole role;

    //каскад
    @OneToMany(mappedBy = "seller",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Offer> offers = new ArrayList<>();
}
