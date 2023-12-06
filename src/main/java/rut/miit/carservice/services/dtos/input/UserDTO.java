package rut.miit.carservice.services.dtos.input;

import lombok.*;
import jakarta.validation.constraints.*;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class UserDTO extends BaseDTO {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean isActive;

    private String imageUrl;

    private UserRoleDTO role; // Вторичный ключ, валидацию не добавляем

    public UserDTO(String username, String password, String firstName, String lastName, Boolean isActive, String imageUrl) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
    }

    @NotEmpty(message = "Username can't be blank!")
    @Size(min = 3,message = "Username must be at least 3 characters long")
    public String getUsername() {
        return username;
    }

    @NotEmpty(message = "Password can't be blank!")
    public String getPassword() {
        return password;
    }

    @NotEmpty(message = "First name can't be blank!")
    public String getFirstName() {
        return firstName;
    }


    @NotEmpty(message = "Last name can't be blank!")
    public String getLastName() {
        return lastName;
    }

//    @NotNull(message = "isActive can't be null!")
    public Boolean getActive() {
        return isActive;
    }

    @NotEmpty(message = "Image URL can't be blank!")
    public String getImageUrl() {
        return imageUrl;
    }

    public UserRoleDTO getRole() {
        return role;
    }
}
