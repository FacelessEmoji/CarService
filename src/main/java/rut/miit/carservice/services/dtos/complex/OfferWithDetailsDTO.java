package rut.miit.carservice.services.dtos.complex;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.services.dtos.base.BaseDTO;

import java.math.BigDecimal;

@Data
public class OfferWithDetailsDTO extends BaseDTO{
    @NotBlank(message = "Description can't be blank!")
    private String description;

    @NotNull(message = "Engine type can't be null!")
    private EngineType engine;

    @NotBlank(message = "Image URL can't be blank!")
    private String imageUrl;

    @NotNull(message = "Mileage can't be null!")
    @Positive(message = "Mileage should be a positive number!")
    private Integer mileage;

    @NotNull(message = "Price can't be null!")
    @Positive(message = "Price should be a positive number!")
    private BigDecimal price;

    @NotNull(message = "Transmission type can't be null!")
    private TransmissionType transmission;

    @NotNull(message = "Year can't be null!")
    @Positive(message = "Year should be a positive number!")
    private Integer year;
    //
    @NotBlank(message = "Model name can't be blank!")
    private String modelName;
    @NotBlank(message = "Brand name can't be blank!")
    private String brandName;
    //
    @NotBlank(message = "Seller username can't be blank!")
    private String sellerUsername;
    @NotBlank(message = "Seller status can't be blank!")
    private Boolean isActive;
}
