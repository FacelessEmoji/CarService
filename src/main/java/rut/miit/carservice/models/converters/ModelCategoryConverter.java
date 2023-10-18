package rut.miit.carservice.models.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import rut.miit.carservice.models.enums.ModelCategory;

@Converter(autoApply = true)
public class ModelCategoryConverter implements AttributeConverter<ModelCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ModelCategory attribute) {
        return attribute == null ? null : attribute.getModelCategoryCode();
    }

    @Override
    public ModelCategory convertToEntityAttribute(Integer dbData) {
        for (ModelCategory modelCategory : ModelCategory.values()) {
            if (modelCategory.getModelCategoryCode() == dbData) {
                return modelCategory;
            }
        }
        throw new IllegalArgumentException("Unknown ModelCategory code: " + dbData);
    }
}
