package rut.miit.carservice.models.enums;

public enum UserRoleType {
    USER(1),
    ADMIN(2);

    private final int userRoleTypeCode;

    private UserRoleType(int userRoleTypeCode) {
        this.userRoleTypeCode = userRoleTypeCode;
    }
}
