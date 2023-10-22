package rut.miit.carservice.dtos.output;

import lombok.*;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.dtos.base.BaseDTO;
import rut.miit.carservice.dtos.base.TimestampedDTO;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarModelOutputDTO extends BaseDTO {
    private String name;
    private ModelCategory category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    //CarBrand
    private String carBrandName;
}
