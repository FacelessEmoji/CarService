package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import lombok.*;
import rut.miit.carservice.models.baseEntities.TimestampedEntity;
import rut.miit.carservice.models.converters.EngineTypeConverter;
import rut.miit.carservice.models.converters.TransmissionTypeConverter;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.TransmissionType;

import java.math.BigDecimal;

@Entity
@Table(name = "offers")
@Data
@NoArgsConstructor
public class Offer extends TimestampedEntity {

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Convert(converter = EngineTypeConverter.class)
    @Column(name = "engine", length = 11, nullable = false)
    private EngineType engine;

    @Column(name = "imageUrl", length = 512, nullable = false)
    private String imageUrl;

    @Column(name = "mileage", length = 11, nullable = false)
    private Integer mileage;

    @Column(name = "price", columnDefinition = "numeric(19,2)", nullable = false)
    private BigDecimal price;

    @Convert(converter = TransmissionTypeConverter.class)
    @Column(name = "transmission", length = 11, nullable = false)
    private TransmissionType transmission;

    @Column(name = "year", length = 11, nullable = false)
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private CarModel model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;
}
