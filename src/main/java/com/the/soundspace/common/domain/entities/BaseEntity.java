package com.the.soundspace.common.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.MIN;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.MIN;

    @PrePersist
    protected void formattingBeforeCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String customLocalDateTime = LocalDateTime.now().format(formatter);
        this.createdAt = LocalDateTime.parse(customLocalDateTime, formatter);
        this.updatedAt = LocalDateTime.parse(customLocalDateTime, formatter);
    }

    @PreUpdate
    protected void formattingBeforeModifiedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String customLocalDateTime = LocalDateTime.now().format(formatter);
        this.updatedAt = LocalDateTime.parse(customLocalDateTime, formatter);
    }
}
