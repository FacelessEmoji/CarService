package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import rut.miit.carservice.models.baseEntities.TimestampedEntity;

import java.util.*;

@Entity
@Table(name = "brands")
public class CarBrand extends TimestampedEntity {
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> models = new ArrayList<>();

    public CarBrand(String name) {
        this.name = name;
    }

    public CarBrand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
