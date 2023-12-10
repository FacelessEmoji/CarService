package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import rut.miit.carservice.models.baseEntities.TimestampedEntity;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends TimestampedEntity  implements Serializable {
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
    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private UserRole role;

    //каскад
    @OneToMany(mappedBy = "seller",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Offer> offers = new ArrayList<>();

    public User(String username, String password, String firstName, String lastName, Boolean isActive, String imageUrl, UserRole role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
