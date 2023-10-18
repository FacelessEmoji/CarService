package rut.miit.carservice.dtos.base;

import java.time.LocalDateTime;

public class TimestampedDTO extends BaseDTO {
    private LocalDateTime created;
    private LocalDateTime modified;
}
