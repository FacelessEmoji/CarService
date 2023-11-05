package rut.miit.carservice.services.dtos.input;

import lombok.*;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.services.dtos.base.BaseDTO;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferDTO extends BaseDTO {
    private String description;
    private EngineType engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private TransmissionType transmission;
    private Integer year;
    //
    private CarModelDTO model;
    private UserDTO seller;
}
