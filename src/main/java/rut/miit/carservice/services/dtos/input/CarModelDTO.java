package rut.miit.carservice.services.dtos.input;

import lombok.*;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.services.dtos.base.BaseDTO;

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
