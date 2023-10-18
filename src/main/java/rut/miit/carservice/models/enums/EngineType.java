package rut.miit.carservice.models.enums;

import lombok.Getter;

@Getter
public enum EngineType {
    GASOLINE(1),
    DIESEL(2),
    ELECTRIC(3),
    HYBRID(4);

    private final int engineTypeCode;

    private EngineType(int engineTypeCode) {
        this.engineTypeCode = engineTypeCode;
    }
}
