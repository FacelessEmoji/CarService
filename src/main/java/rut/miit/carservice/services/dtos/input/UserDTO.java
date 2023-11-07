package rut.miit.carservice.services.dtos.input;

import lombok.*;
import jakarta.validation.constraints.*;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO extends BaseDTO {
    @NotBlank(message = "Username can't be blank!")
    private String username;

    @NotBlank(message = "Password can't be blank!")
    private String password;

    @NotBlank(message = "First name can't be blank!")
    private String firstName;

    @NotBlank(message = "Last name can't be blank!")
    private String lastName;

    @NotNull(message = "isActive can't be null!")
    private Boolean isActive;

    @NotBlank(message = "Image URL can't be blank!")
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
}
