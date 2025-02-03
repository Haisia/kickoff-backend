package com.kickoff.membership.service.dataaccess.entity;

import entity.BaseJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Table(name = "members")
@Entity
public class Member extends BaseJpaEntity {
  @Id
  private UUID id;
  private String email;
  private String password;
  private BigDecimal point;

  public Member(UUID id) {
    this.id = id;
  }
}
