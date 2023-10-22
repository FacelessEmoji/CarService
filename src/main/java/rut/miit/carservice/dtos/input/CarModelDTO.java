package rut.miit.carservice.dtos.input;

import lombok.*;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.dtos.base.BaseDTO;
import rut.miit.carservice.dtos.base.TimestampedDTO;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarModelDTO extends BaseDTO {
    private String name;
    private ModelCategory category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    //
    private CarBrandDTO brandDTO;
}
