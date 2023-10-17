package rut.miit.carservice.dtos;

import lombok.Data;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OfferWithDetailsDTO {
    private UUID id;
    private String description;
    private EngineType engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private TransmissionType transmission;
    private Integer year;
    //
    private String modelName;
    private String brandName;
    //
    private String sellerUsername;
    private Boolean isActive;
}
