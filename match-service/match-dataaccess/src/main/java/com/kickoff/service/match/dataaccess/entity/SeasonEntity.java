package com.kickoff.service.match.dataaccess.entity;

import com.kickoff.common.dataaccess.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Table(name = "seasons")
@Entity
public class SeasonEntity extends BaseJpaEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "league_id")
  private LeagueEntity league;

  private Integer year;
  private LocalDate startDate;
  private LocalDate endDate;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonEntity that = (SeasonEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
