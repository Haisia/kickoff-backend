package com.kickoff.common.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
  @CreatedDate
  @Column(updatable = false)
  protected LocalDateTime createdAt;

  @LastModifiedDate
  @Column(insertable = false)
  protected LocalDateTime updatedAt;
}
