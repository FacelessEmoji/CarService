package rut.miit.carservice.services.dtos.input;

import lombok.*;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarBrandDTO extends BaseDTO {
    private String name;
}
