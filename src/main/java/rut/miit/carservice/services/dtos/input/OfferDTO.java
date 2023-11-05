package rut.miit.carservice.services.dtos.input;

import lombok.*;
import jakarta.validation.constraints.*;
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

    private CarModelDTO model; // Вторичный ключ, валидацию не добавляем
    private UserDTO seller; // Вторичный ключ, валидацию не добавляем
}
