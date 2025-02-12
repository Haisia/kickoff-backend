package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.domain.valuobject.LeagueType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
public class League extends AggregateRoot<LeagueId> {

  private Long apiFootballLeagueId;
  private String name;
  private LeagueType type;
  private Logo logo;
  private Country country;

  private List<Season> seasons;

  @Builder
  public League(LeagueId id, Long apiFootballLeagueId, String name, LeagueType type, Logo logo, Country country, List<Season> seasons) {
    if(id == null || id.getValue() == null) id = LeagueId.of(UUID.randomUUID());
    this.id = id;

    this.apiFootballLeagueId = apiFootballLeagueId;
    this.name = name;
    this.type = type;
    this.logo = logo;
    this.country = country;

    seasons.forEach(season -> season.setLeagueId(this.id));
    this.seasons = seasons;
  }
}
