package rut.miit.carservice.services.dtos.output;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserOutputDTO extends BaseDTO{
    @NotBlank(message = "Username can't be blank!")
    private String username;

    @NotBlank(message = "First name can't be blank!")
    private String firstName;

    @NotBlank(message = "Last name can't be blank!")
    private String lastName;

    @NotNull(message = "isActive can't be null!")
    private Boolean isActive;

    @NotBlank(message = "Image URL can't be blank!")
    private String imageUrl;
    //
    @NotNull(message = "Role can't be null!")
    private String role;
}
