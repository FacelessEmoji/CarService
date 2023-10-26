package rut.miit.carservice.models.entities;

import jakarta.persistence.*;
import rut.miit.carservice.models.baseEntities.TimestampedEntity;
import rut.miit.carservice.models.converters.ModelCategoryConverter;
import rut.miit.carservice.models.enums.ModelCategory;

@Entity
@Table(name = "models")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private CarBrand brand;

    public CarModel(String name, ModelCategory category, String imageUrl, Integer startYear, Integer endYear, CarBrand brand) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
    }

    public CarModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelCategory getCategory() {
        return category;
    }

    public void setCategory(ModelCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }
}
