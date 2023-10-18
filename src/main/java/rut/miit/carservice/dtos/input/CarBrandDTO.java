package rut.miit.carservice.dtos.input;

import lombok.Data;
import rut.miit.carservice.dtos.base.BaseDTO;
import rut.miit.carservice.dtos.base.TimestampedDTO;

@Data
public class CarBrandDTO extends BaseDTO {
    private String name;
}
