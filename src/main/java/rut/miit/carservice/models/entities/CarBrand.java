package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import lombok.*;
import rut.miit.carservice.models.baseEntities.TimestampedEntity;

import java.util.*;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
public class CarBrand extends TimestampedEntity {
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> models = new ArrayList<>();

    public CarBrand(String name) {
        this.name = name;
    }
}
