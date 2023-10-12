package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import lombok.*;
import rut.miit.carservice.models.baseEntities.TimestampedEntity;
import rut.miit.carservice.models.enums.ModelCategory;

@Entity
@Table(name = "models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarModel extends TimestampedEntity{

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ModelCategory category;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "startYear")
    private Integer startYear;

    @Column(name = "endYear")
    private Integer endYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private CarBrand brand;
}
