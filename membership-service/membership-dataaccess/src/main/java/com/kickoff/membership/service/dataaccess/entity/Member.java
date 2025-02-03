package com.kickoff.membership.service.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "members")
@Entity
public class Member {
  @Id
  private UUID id;
  private String email;
  private String password;
  private BigDecimal point;

  public Member() {
  }

  public Member(UUID id, String email, String password, BigDecimal point) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.point = point;
  }
}
