package com.kickoff.membership.dataaccess.entity;

import com.kickoff.common.dataaccess.entity.BaseJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "members")
@Entity
public class MemberEntity extends BaseJpaEntity {
  @Id
  private UUID id;
  @Column(unique = true)
  private String email;
  private String password;
  private BigDecimal point;

  public MemberEntity(UUID id) {
    this.id = id;
  }
}
