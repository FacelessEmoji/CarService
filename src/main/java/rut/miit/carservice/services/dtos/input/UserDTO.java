package rut.miit.carservice.services.dtos.input;

import lombok.*;
import jakarta.validation.constraints.*;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.services.dtos.base.BaseDTO;
import rut.miit.carservice.util.viewValidators.User.NotAllUsername;
import rut.miit.carservice.util.viewValidators.User.UniqueUsername;
import rut.miit.carservice.util.viewValidators.User.ValidName;
import rut.miit.carservice.util.viewValidators.User.ValidPassword;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class UserDTO extends BaseDTO {

    @UniqueUsername
    @NotAllUsername
    private String username;

    @ValidPassword
    private String password;

    @ValidName
    private String firstName;

    @ValidName
    private String lastName;

    private Boolean isActive;

    private String imageUrl;

    private UserRoleDTO role; // Вторичный ключ, валидацию не добавляем


    @NotNull(message = "Username can't be blank!")
    @Size(min = 3,message = "Username must be at least 3 characters long!")
    public String getUsername() {
        return username;
    }

    @NotNull(message = "Password can't be blank!")
    @Size(min = 8, max = 20, message = "Password must be from 8 to 20 characters long!")
    public String getPassword() {
        return password;
    }

    @NotNull(message = "First name can't be blank!")
    public String getFirstName() {
        return firstName;
    }


    @NotNull(message = "Last name can't be blank!")
    public String getLastName() {
        return lastName;
    }

    @NotNull(message = "isActive can't be null!")
    public Boolean getActive() {
        return isActive;
    }

    @NotNull(message = "Image URL can't be blank!")
    public String getImageUrl() {
        return imageUrl;
    }

    public UserRoleDTO getRole() {
        return role;
    }
}
