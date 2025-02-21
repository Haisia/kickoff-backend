package com.kickoff.service.match.domain.dto.fixture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Data
public class LeagueFixtureResponse {
  League league;
  List<FixtureResponse> fixtures;

  public static LeagueFixtureResponse from(com.kickoff.service.match.domain.entity.League league, Integer year, List<FixtureResponse> fixtures) {
    return new LeagueFixtureResponse(from(league, year), fixtures);
  }

  private static League from(com.kickoff.service.match.domain.entity.League league, Integer year) {
    return League.builder()
      .id(league.getId().getId())
      .name(league.getName())
      .logo(league.getLogoUrlAnything())
      .year(year)
      .build();
  }

  @Builder
  @Data
  private static class League {
    public UUID id;
    public String name;
    public String logo;
    public Integer year;
  }
}
