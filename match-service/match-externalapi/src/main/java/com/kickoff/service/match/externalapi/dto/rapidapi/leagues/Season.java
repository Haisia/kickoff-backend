package com.kickoff.service.match.externalapi.dto.rapidapi.leagues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Data
public class Season {
  public Integer year;
  public LocalDate start;
  public LocalDate end;
  public Boolean current;
  public Coverage coverage;

  public static class Coverage {
    public Fixtures fixtures;

    public Boolean standings;
    public Boolean players;
    public Boolean top_corers;
    public Boolean top_assists;
    public Boolean top_cards;
    public Boolean injuries;
    public Boolean predictions;
    public Boolean odds;

    public static class Fixtures{
      public Boolean events;
      public Boolean lineups;
      public Boolean statistics_fixtures;
      public Boolean statistics_players;
    }
  }
}
