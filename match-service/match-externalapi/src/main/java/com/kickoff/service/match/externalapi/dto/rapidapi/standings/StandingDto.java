package com.kickoff.service.match.externalapi.dto.rapidapi.standings;

import com.kickoff.service.match.externalapi.dto.rapidapi.teams.TeamDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandingDto {
  public Integer rank;
  public TeamDto team;
  public Integer points;
  public Integer goalsDiff;
  public String form;
  public String status;
  public ScoreStatsDto all;
  public ScoreStatsDto home;
  public ScoreStatsDto away;


}
