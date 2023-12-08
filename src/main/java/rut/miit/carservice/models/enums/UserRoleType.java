package rut.miit.carservice.models.enums;

import lombok.Getter;

@Getter
public enum UserRoleType {
    USER(1),
    MODERATOR(2),
    ADMIN(3);

    private final int userRoleTypeCode;

    UserRoleType(int userRoleTypeCode) {
        this.userRoleTypeCode = userRoleTypeCode;
    }
}
