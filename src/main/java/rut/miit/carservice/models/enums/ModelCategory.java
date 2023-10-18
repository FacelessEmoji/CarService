package rut.miit.carservice.models.enums;

public enum ModelCategory {
    CAR(1),
    BUSS(2),
    TRUCK(3),
    MOTORCYCLE(4);

    private final int modelCategoryCode;

    private ModelCategory(int modelCategoryCode) {
        this.modelCategoryCode = modelCategoryCode;
    }
}
