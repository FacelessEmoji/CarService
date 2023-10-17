package rut.miit.carservice.dtos;

import lombok.Data;
import rut.miit.carservice.models.enums.ModelCategory;

import java.util.UUID;

@Data
public class CarModelWithBrandDTO {
    private UUID id;
    private String name;
    private ModelCategory category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    //CarBrand
    private String carBrandName;
}
