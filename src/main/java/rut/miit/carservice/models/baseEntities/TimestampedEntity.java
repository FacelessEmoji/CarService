package rut.miit.carservice.models.baseEntities;

import jakarta.persistence.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class TimestampedEntity extends BaseEntity {
    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private LocalDateTime modified;

    public LocalDateTime getCreated() {
        return created;
    }

    private void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    private void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}

