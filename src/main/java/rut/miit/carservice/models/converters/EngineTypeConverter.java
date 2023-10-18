package rut.miit.carservice.models.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import rut.miit.carservice.models.enums.EngineType;

@Converter(autoApply = true)
public class EngineTypeConverter implements AttributeConverter<EngineType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EngineType attribute) {
        return attribute == null ? null : attribute.getEngineTypeCode();
    }

    @Override
    public EngineType convertToEntityAttribute(Integer dbData) {
        for (EngineType engineType : EngineType.values()) {
            if (engineType.getEngineTypeCode() == dbData) {
                return engineType;
            }
        }
        throw new IllegalArgumentException("Unknown EngineType code: " + dbData);
    }
}
