package com.kickoff.service.match.externalapi.dto.rapidapi.fixtures;

import com.kickoff.service.match.domain.entity.Fixture;
import com.kickoff.service.match.domain.entity.FixtureStatistic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor @AllArgsConstructor
@Data
public class FixturesStatisticsResponse {

  Team team;
  List<Statistic> statistics = new ArrayList<>();

  public List<FixtureStatistic> toFixtureStatistics(Fixture fixture) {
    com.kickoff.service.match.domain.entity.Team homeTeam = fixture.getHomeTeam();
    com.kickoff.service.match.domain.entity.Team awayTeam = fixture.getAwayTeam();

    com.kickoff.service.match.domain.entity.Team targetTeam;
    if (team.getId().equals(homeTeam.getApiFootballTeamId())) {
      targetTeam = homeTeam;
    } else if (team.getId().equals(awayTeam.getApiFootballTeamId())) {
      targetTeam = awayTeam;
    } else {
      return null;
    }

    return statistics.stream()
      .map(statistic -> statistic.toFixtureStatistic(fixture, targetTeam))
      .collect(Collectors.toList());
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class Team {
    Long id;
    String name;
    String logo;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class Statistic {
    String type;
    String value;

    FixtureStatistic toFixtureStatistic(Fixture fixture, com.kickoff.service.match.domain.entity.Team team) {
      return FixtureStatistic.builder()
        .fixture(fixture)
        .team(team)
        .type(type)
        .value(value)
        .build();
    }
  }

}
