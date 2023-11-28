package rut.miit.carservice.services.dtos.input;

import jakarta.validation.constraints.*;
import lombok.*;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.services.dtos.base.BaseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class CarModelDTO extends BaseDTO {

    private String name;

    private ModelCategory category;

    private String imageUrl;

    private Integer startYear;

    private Integer endYear;

    private String brandId;

    public CarModelDTO(String name, String category, String imageUrl, Integer startYear, Integer endYear, String brandId) {
        this.name = name;
        this.category = ModelCategory.valueOf(category);
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brandId = brandId;
    }

    @NotBlank(message = "Model name can't be blank!")
    @Size(min = 2, max = 50,message = "Model name need to be from 2 to 50 symbols long!")
    public String getName() {
        return name;
    }

    @NotNull(message = "Category can't be null!")
    public ModelCategory getCategory() {
        return category;
    }

    @NotBlank(message = "Image URL can't be blank!")
    public String getImageUrl() {
        return imageUrl;
    }

    @NotNull(message = "Start year can't be null!")
    @Positive(message = "Start year should be a positive number!")
    public Integer getStartYear() {
        return startYear;
    }

    @NotNull(message = "End year can't be null!")
    @Positive(message = "End year should be a positive number!")
    public Integer getEndYear() {
        return endYear;
    }

    public String getBrandId() {
        return brandId;
    }
}
