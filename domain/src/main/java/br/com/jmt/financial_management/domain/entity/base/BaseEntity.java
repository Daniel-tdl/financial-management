package br.com.jmt.financial_management.domain.entity.base;

import br.com.jmt.financial_management.domain.util.ZoneUtils;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void basePrePersist() {
        if (this.createdAt == null) createdAt = LocalDateTime.now(ZoneUtils.getSaoPauloZone());
        if (this.updatedAt == null) this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void basePreUpdate() {
        this.updatedAt = LocalDateTime.now(ZoneUtils.getSaoPauloZone());
    }
}
