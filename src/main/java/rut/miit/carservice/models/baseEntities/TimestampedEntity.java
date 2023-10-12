package rut.miit.carservice.models.baseEntities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class TimestampedEntity extends BaseEntity {
    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private LocalDateTime modified;
}
