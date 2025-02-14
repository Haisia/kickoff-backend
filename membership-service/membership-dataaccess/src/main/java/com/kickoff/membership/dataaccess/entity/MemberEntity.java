package com.kickoff.membership.dataaccess.entity;

import com.kickoff.common.dataaccess.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@Getter @Setter
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

  @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
  List<AttendanceRecordEntity> attendanceRecords = new ArrayList<>();

  public MemberEntity(UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    MemberEntity that = (MemberEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
