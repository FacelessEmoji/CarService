package rut.miit.carservice.models.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import rut.miit.carservice.models.enums.UserRoleType;


@Converter(autoApply = true)
public class RoleTypeConverter implements AttributeConverter<UserRoleType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRoleType attribute) {
        return attribute == null ? null : attribute.getUserRoleTypeCode();
    }

    @Override
    public UserRoleType convertToEntityAttribute(Integer dbData) {
        for (UserRoleType roleType : UserRoleType.values()) {
            if (roleType.getUserRoleTypeCode() == dbData) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
