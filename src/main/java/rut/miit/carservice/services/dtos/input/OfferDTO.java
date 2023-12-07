package rut.miit.carservice.services.dtos.input;

import lombok.*;
import jakarta.validation.constraints.*;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.services.dtos.base.BaseDTO;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
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

    private String model; // Вторичный ключ, валидацию не добавляем
    private String seller; // Вторичный ключ, валидацию не добавляем

    @NotBlank(message = "Description can't be blank!")
    @Size(min = 10, max = 1000,message = "Description need to be from 10 to 1000 symbols long!")
    public String getDescription() {
        return description;
    }

    @NotNull(message = "Engine type can't be null!")
    public EngineType getEngine() {
        return engine;
    }

    @NotBlank(message = "Image URL can't be blank!")
    public String getImageUrl() {
        return imageUrl;
    }

    @NotNull(message = "Mileage can't be null!")
    @Positive(message = "Mileage should be a positive number!")
    public Integer getMileage() {
        return mileage;
    }

    @NotNull(message = "Price can't be null!")
    @Positive(message = "Price should be a positive number!")
    public BigDecimal getPrice() {
        return price;
    }

    @NotNull(message = "Transmission type can't be null!")
    public TransmissionType getTransmission() {
        return transmission;
    }

    @NotNull(message = "Year can't be null!")
    @DecimalMin(value = "1900", message = "Year should be more than 1900!")
    @DecimalMax(value = "2100", message = "Year should be less than 2100!")
    public Integer getYear() {
        return year;
    }

    @NotNull(message = "Model id can't be null!")
    public String getModel() {
        return model;
    }

    public String getSeller() {
        return seller;
    }
}
