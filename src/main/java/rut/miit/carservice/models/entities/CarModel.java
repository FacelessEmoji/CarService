package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import lombok.*;
import rut.miit.carservice.models.baseEntities.TimestampedEntity;
import rut.miit.carservice.models.converters.ModelCategoryConverter;
import rut.miit.carservice.models.enums.ModelCategory;

@Entity
@Table(name = "models")
@Data
@NoArgsConstructor
public class CarModel extends TimestampedEntity{

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Convert(converter = ModelCategoryConverter.class)
    @Column(name = "category", length = 11, nullable = false)
    private ModelCategory category;

    @Column(name = "imageUrl", length = 512, nullable = false)
    private String imageUrl;

    @Column(name = "startYear", length = 11, nullable = false)
    private Integer startYear;

    @Column(name = "endYear", length = 11, nullable = false)
    private Integer endYear;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private CarBrand brand;
}
