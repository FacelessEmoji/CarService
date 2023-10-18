package rut.miit.carservice.dtos.output;

import lombok.Data;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.dtos.base.BaseDTO;
import rut.miit.carservice.dtos.base.TimestampedDTO;


@Data
public class CarModelOutputDTO extends BaseDTO {
    private String name;
    private ModelCategory category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    //CarBrand
    private String carBrandName;
}
