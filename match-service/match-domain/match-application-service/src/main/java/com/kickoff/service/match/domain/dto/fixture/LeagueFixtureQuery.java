package com.kickoff.service.match.domain.dto.fixture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Data
public class LeagueFixtureQuery {
  public UUID leagueId;
  public Year year;
  public UUID fixtureId;
}
