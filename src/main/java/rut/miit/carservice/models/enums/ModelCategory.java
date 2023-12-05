package rut.miit.carservice.models.enums;

import lombok.Getter;

@Getter
public enum ModelCategory {
    CAR(1),
    BUS(2),
    TRUCK(3),
    MOTORCYCLE(4);

    private final int modelCategoryCode;

    private ModelCategory(int modelCategoryCode) {
        this.modelCategoryCode = modelCategoryCode;
    }
}
