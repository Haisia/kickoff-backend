package com.kickoff.service.match.domain.dto.rank;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class LeagueTeamsResponse {
  public League league;
  public Integer season;
  public List<Team> teams;

  @Builder
  @Data
  public static class League {
    public UUID id;
    public String name;
    public String logo;
  }

  @Builder
  @Data
  public static class Team {
    public UUID id;
    public String name;
    public String code;
    public String logo;

    public Integer rank;
    public String rankStatus;
    public Integer points;
    public Integer goalsDiff;
    public String form;

    public Integer played;
    public Integer win;
    public Integer draw;
    public Integer lose;
    public Integer goalsFor;
    public Integer goalsAgainst;
  }
}
