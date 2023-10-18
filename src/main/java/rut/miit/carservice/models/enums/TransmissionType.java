package rut.miit.carservice.models.enums;

import lombok.Getter;

@Getter
public enum TransmissionType {
    MANUAL(1),
    AUTOMATIC(2);

    private final int transmissionTypeCode;

    private TransmissionType(int transmissionTypeCode) {
        this.transmissionTypeCode = transmissionTypeCode;
    }
}
