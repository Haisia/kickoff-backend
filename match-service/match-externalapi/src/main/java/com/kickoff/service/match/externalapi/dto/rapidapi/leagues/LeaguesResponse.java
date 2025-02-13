package com.kickoff.service.match.externalapi.dto.rapidapi.leagues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data
public class LeaguesResponse {
  public LeagueDto league;
  public CountryDto country;
  public List<SeasonDto> seasons;
}
