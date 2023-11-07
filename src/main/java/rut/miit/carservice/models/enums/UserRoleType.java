package rut.miit.carservice.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserRoleType {
    USER(1),
    ADMIN(2);

    private final int userRoleTypeCode;

    private UserRoleType(int userRoleTypeCode) {
        this.userRoleTypeCode = userRoleTypeCode;
    }

//    @JsonValue
//    public String toString() {
//        return name();
//    }
//
//    @JsonCreator
//    public static UserRoleType fromString(String value) {
//        return UserRoleType.valueOf(value);
//    }
}
