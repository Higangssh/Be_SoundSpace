package com.the.soundspace.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
