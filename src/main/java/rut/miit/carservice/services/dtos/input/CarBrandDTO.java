package rut.miit.carservice.services.dtos.input;

import jakarta.validation.constraints.*;
import lombok.*;
import rut.miit.carservice.services.dtos.base.BaseDTO;
import rut.miit.carservice.util.viewValidators.Brand.NotAllBrandName;
import rut.miit.carservice.util.viewValidators.Brand.UniqueBrandName;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class CarBrandDTO extends BaseDTO {

    @UniqueBrandName
    @NotAllBrandName
    private String name;

    @NotBlank(message = "Brand name can't be blank!")
    @Size(min = 2, max = 30,message = "Brand name need to be from 2 to 30 symbols long!")
    public String getName() {
        return name;
    }
}
