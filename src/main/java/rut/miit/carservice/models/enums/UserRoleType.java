package rut.miit.carservice.models.enums;

import lombok.Getter;

@Getter
public enum UserRoleType {
    USER(1),
    ADMIN(2);

    private final int userRoleTypeCode;

    private UserRoleType(int userRoleTypeCode) {
        this.userRoleTypeCode = userRoleTypeCode;
    }
}
