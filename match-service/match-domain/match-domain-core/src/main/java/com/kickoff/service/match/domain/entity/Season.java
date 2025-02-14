package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.service.match.domain.valueobject.SeasonId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
public class Season extends BaseEntity {
  @EmbeddedId
  private SeasonId id;
  private LeagueId leagueId;
  private Year year;
  private LocalDate startDate;
  private LocalDate endDate;

  @Builder
  public Season(SeasonId id, LeagueId leagueId, Year year, LocalDate startDate, LocalDate endDate) {
    if (id == null) id = SeasonId.generate();
    this.id = id;
    this.leagueId = leagueId;
    this.year = year;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Season season = (Season) o;
    return Objects.equals(id, season.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
