package rut.miit.carservice.services.dtos.input;

import jakarta.validation.constraints.*;
import lombok.*;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarBrandDTO extends BaseDTO {
    @NotBlank(message = "Brand name can't be blank!")
    @Size(min = 2, max = 30,message = "Brand name need to be from 2 to 30 symbols long!")
    private String name;
}
