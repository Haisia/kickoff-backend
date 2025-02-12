package com.kickoff.service.match.dataaccess.entity;

import com.kickoff.common.dataaccess.entity.BaseJpaEntity;
import com.kickoff.service.match.domain.valueobject.UrlType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Table(name = "flags")
@Entity
public class FlagEntity extends BaseJpaEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;
  private String url;
  @Enumerated(EnumType.STRING)
  private UrlType urlType;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FlagEntity that = (FlagEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
