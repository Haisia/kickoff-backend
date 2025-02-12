package com.kickoff.service.match.dataaccess.entity;

import com.kickoff.common.dataaccess.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Table(name = "countries")
@Entity
public class CountryEntity extends BaseJpaEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;
  private String name;
  private String code;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "flag_id")
  private FlagEntity flag;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    CountryEntity that = (CountryEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
