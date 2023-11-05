package rut.miit.carservice.services.dtos.input;

import jakarta.validation.constraints.*;
import lombok.*;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarModelDTO extends BaseDTO {
    @NotBlank(message = "Model name can't be blank!")
    @Size(min = 2, max = 50,message = "Model name need to be from 2 to 50 symbols long!")
    private String name;

    @NotNull(message = "Category can't be null!")
    private ModelCategory category;

    @NotBlank(message = "Image URL can't be blank!")
    private String imageUrl;

    @NotNull(message = "Start year can't be null!")
    @Positive(message = "Start year should be a positive number!")
    private Integer startYear;

    @NotNull(message = "End year can't be null!")
    @Positive(message = "End year should be a positive number!")
    private Integer endYear;

    private CarBrandDTO brandDTO; // Вторичный ключ, валидацию не добавляем
}
