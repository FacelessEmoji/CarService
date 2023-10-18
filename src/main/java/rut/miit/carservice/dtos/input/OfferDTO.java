package rut.miit.carservice.dtos.input;

import lombok.Data;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.dtos.base.BaseDTO;

import java.math.BigDecimal;

@Data
public class OfferDTO extends BaseDTO{
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
