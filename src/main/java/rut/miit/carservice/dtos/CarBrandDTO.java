package rut.miit.carservice.dtos;

import lombok.Data;
import java.util.UUID;

@Data
public class CarBrandDTO {
    private UUID id;
    private String name;
}
