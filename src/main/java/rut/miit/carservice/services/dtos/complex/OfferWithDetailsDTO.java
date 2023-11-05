package rut.miit.carservice.services.dtos.complex;

import lombok.Data;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.services.dtos.base.BaseDTO;

import java.math.BigDecimal;

@Data
public class OfferWithDetailsDTO extends BaseDTO{
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
