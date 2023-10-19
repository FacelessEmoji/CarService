package rut.miit.carservice.dtos.input;

import lombok.*;
import rut.miit.carservice.dtos.base.BaseDTO;
import rut.miit.carservice.dtos.base.TimestampedDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarBrandDTO extends BaseDTO {
    private String name;
}
