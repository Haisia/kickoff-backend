package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.domain.valuobject.LeagueType;
import com.kickoff.service.match.domain.valueobject.Country;
import com.kickoff.service.match.domain.valueobject.Logo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
public class League extends AggregateRoot {
  @EmbeddedId
  private LeagueId id;
  private Long apiFootballLeagueId;
  private String name;
  @Enumerated(EnumType.STRING)
  private LeagueType type;

  @ElementCollection
  @CollectionTable(name = "league_logos", joinColumns = @JoinColumn(name = "league_id"))
  private List<Logo> logos = new ArrayList<>();

  @Embedded
  private Country country;

  @ElementCollection
  @CollectionTable(name = "season_map_team", joinColumns = @JoinColumn(name = "league_id"))
  private List<SeasonMapTeam> seasonMapTeams = new ArrayList<>();

  @Builder
  public League(LeagueId id, Long apiFootballLeagueId, String name, LeagueType type, Country country) {
    if (id == null) id = LeagueId.generate();
    this.id = id;
    this.apiFootballLeagueId = apiFootballLeagueId;
    this.name = name;
    this.type = type;
    this.country = country;
  }

  public void addLogo(Logo logo) {
    logos.add(logo);
  }

  public void addSeason(Season season) {
    season.setLeagueId(id);
    seasonMapTeams.add(new SeasonMapTeam(season, null));
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    League league = (League) o;
    return Objects.equals(id, league.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}