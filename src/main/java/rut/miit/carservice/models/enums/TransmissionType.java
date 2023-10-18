package rut.miit.carservice.models.enums;

public enum TransmissionType {
    MANUAL(1),
    AUTOMATIC(2);

    private final int transmissionTypeCode;

    private TransmissionType(int transmissionTypeCode) {
        this.transmissionTypeCode = transmissionTypeCode;
    }
}
