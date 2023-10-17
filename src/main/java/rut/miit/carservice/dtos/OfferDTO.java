package rut.miit.carservice.dtos;

import lombok.Data;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OfferDTO {
    private UUID id;
    private String description;
    private EngineType engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private TransmissionType transmission;
    private Integer year;
    //
    private CarModelWithBrandDTO model;
    private UserWithRoleDTO seller;
}
