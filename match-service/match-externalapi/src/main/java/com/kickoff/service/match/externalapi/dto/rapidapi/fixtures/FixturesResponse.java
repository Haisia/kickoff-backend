package com.kickoff.service.match.externalapi.dto.rapidapi.fixtures;

import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class FixturesResponse {
  public FixtureDto fixture;
  public LeagueDto league;
  public TeamsDto teams;

  public ScoreDto goals;
  public ScoresDto score;

  @NoArgsConstructor @AllArgsConstructor
  @Data
  public static class TeamsDto {
    public TeamDto home;
    public TeamDto away;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  public static class LeagueDto {
    public Long id;
    public String name;
    public String country;
    public String logo;
    public String flag;
    public Integer season;
    public String round;
    public Boolean standings;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  public static class ScoreDto {
    public Integer home;
    public Integer away;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  public static class ScoresDto {
    public ScoreDto halftime;
    public ScoreDto fulltime;
    public ScoreDto extratime;
    public ScoreDto penalty;
  }
}
