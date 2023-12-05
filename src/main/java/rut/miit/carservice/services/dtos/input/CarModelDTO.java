package rut.miit.carservice.services.dtos.input;

import jakarta.validation.constraints.*;
import lombok.*;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.services.dtos.base.BaseDTO;
import rut.miit.carservice.util.viewValidators.Model.NotAllName;
import rut.miit.carservice.util.viewValidators.Model.UniqueModelNameWithinBrand;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class CarModelDTO extends BaseDTO {
    @UniqueModelNameWithinBrand
    @NotAllName
    private String name;

    private ModelCategory category;

    private String imageUrl;

    private Integer startYear;

    private Integer endYear;

    private String brand;

    public CarModelDTO(String name, String category, String imageUrl, Integer startYear, Integer endYear, String brand) {
        this.name = name;
        this.category = ModelCategory.valueOf(category);
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
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

    @DecimalMin(value = "1900", message = "End year should be more than 1900!")
    @NotNull(message = "Start year can't be null!")
    public Integer getStartYear() {
        return startYear;
    }

    @DecimalMax(value = "2100", message = "Start year should be less than 2100!")
    @NotNull(message = "End year can't be null!")
    public Integer getEndYear() {
        return endYear;
    }

    @NotNull(message = "Brand ID can't be null!")
    public String getBrand() {
        return brand;
    }
}
