package com.kickoff.service.match.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.valuobject.LeagueId;
import com.kickoff.common.domain.valuobject.LeagueType;
import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.service.match.domain.exception.LeagueDomainException;
import com.kickoff.service.match.domain.valueobject.Country;
import com.kickoff.service.match.domain.valueobject.Logo;
import com.kickoff.service.match.domain.valueobject.TeamId;
import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "leagues")
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

  @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Team> allTeamsInLeague = new ArrayList<>();

  @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Season> allSeasonsInLeague = new ArrayList<>();

  @Builder
  public League(LeagueId id, Long apiFootballLeagueId, String name, LeagueType type, Country country) {
    if (id == null) id = LeagueId.generate();
    this.id = id;
    this.apiFootballLeagueId = apiFootballLeagueId;
    this.name = name;
    this.type = type;
    this.country = country;
  }

  public void addTeamWithSeason(Season season, Team team) {
    if (season == null || team == null) return;
    if (!hasSeason(season.getYear())) addSeason(season);
    if (!hasTeam(team.getApiFootballTeamId())) addTeam(team);

    season = getSeasonByYear(season.getYear())
      .orElseThrow(() -> new LeagueDomainException("[*] season 을 찾을 수 없습니다", CustomHttpStatus.INTERNAL_SERVER_ERROR));
    team = getTeamByApIFootballTeamId(team.getApiFootballTeamId())
      .orElseThrow(() -> new LeagueDomainException("[*] team 을 찾을 수 없습니다", CustomHttpStatus.INTERNAL_SERVER_ERROR));

    season.setLeague(this);
    team.setLeague(this);
    SeasonMapTeam seasonMapTeam = SeasonMapTeam.builder()
      .season(season)
      .team(team)
      .build();
    if (!seasonMapTeams.contains(seasonMapTeam)) seasonMapTeams.add(seasonMapTeam);
  }

  public void addTeamWithSeason(Season season, TeamId teamId) {
    Team team = getTeamByTeamId(teamId)
      .orElseThrow(() -> new LeagueDomainException("[*] team 을 찾을 수 없습니다", CustomHttpStatus.BAD_REQUEST));

    addTeamWithSeason(season, team);
  }

  public void addSeason(Season season) {
    if (season == null) return;
    if (getSeasonByYear(season.getYear()).isPresent()) return;

    season.setLeague(this);
    allSeasonsInLeague.add(season);
  }

  public boolean hasSeason(Year year) {
    return getSeasonByYear(year).isPresent();
  }

  public Optional<Season> getSeasonByYear(Year year) {
    return allSeasonsInLeague.stream()
      .filter(season -> season.getYear().equals(year))
      .findFirst()
      ;
  }

  public Optional<SeasonMapTeam> getSeasonMapTeam(Year year, Long apiFootballTeamId) {
    return seasonMapTeams.stream()
      .filter(m -> m.getSeason().getYear().equals(year) && m.getTeam().getApiFootballTeamId().equals(apiFootballTeamId))
      .findFirst();
  }

  public void addTeam(Team team) {
    if (team == null) return;
    if (allTeamsInLeague.contains(team)) return;
    if (getTeamByApIFootballTeamId(team.getApiFootballTeamId()).isPresent()) return;

    team.setLeague(this);
    allTeamsInLeague.add(team);
  }

  public void addTeams(List<Team> teams) {
    teams.forEach(this::addTeam);
  }

  public boolean hasTeam(TeamId teamId) {
    return getTeamByTeamId(teamId).isPresent();
  }

  public boolean hasTeam(Long apiFootballTeamId) {
    return getTeamByApIFootballTeamId(apiFootballTeamId).isPresent();
  }

  public Optional<Team> getTeamByTeamId(TeamId teamId) {
    return allTeamsInLeague.stream()
      .filter(team -> team.getId().equals(teamId))
      .findFirst()
      ;
  }

  public Optional<Team> getTeamByApIFootballTeamId(Long apiFootballLeagueId) {
    return allTeamsInLeague.stream()
      .filter(team -> team.getApiFootballTeamId().equals(apiFootballLeagueId))
      .findFirst()
      ;
  }

  public void addLogo(Logo logo) {
    logos.add(logo);
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