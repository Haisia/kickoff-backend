package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.BaseEntity;
import com.kickoff.common.domain.valuobject.LeagueId;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Season extends BaseEntity<Long> {
  private LeagueId leagueId;
  private Year year;
  private LocalDate startDate;
  private LocalDate endDate;

  private List<Team> teams;

  @Builder
  public Season(Long id, LeagueId leagueId, Year year, LocalDate startDate, LocalDate endDate) {
    this.id = id;
    this.leagueId = leagueId;
    this.year = year;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
