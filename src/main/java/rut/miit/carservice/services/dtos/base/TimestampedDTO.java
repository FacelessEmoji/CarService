package rut.miit.carservice.services.dtos.base;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimestampedDTO extends BaseDTO {
    private LocalDateTime created;
    private LocalDateTime modified;
}
